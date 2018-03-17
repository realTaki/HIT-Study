# -*- coding: cp936 -*-
import socket, select, sys

# 一个聊天服务器的脚本，可以用客户端脚本接入

def broadcast_to_client(sock, message):
    for s in socks:
        if s != server_sock and s != sock:
            try:
                s.send(message)
            except Exception, e:
                print(str(e))
                s.close()
                socks.remove(s)


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print "Usage: python server_test.py port"
        sys.exit()

    port = int(sys.argv[1])
    socks = []

    server_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_sock.bind(("0.0.0.0", port))
    server_sock.listen(10)

    socks.append(server_sock)

    print("Server is running on port: " + str(port))

    while True:
        r_socks, w_socks, e_socks = select.select(socks, [], [])
        for sock in r_socks:
            if sock == server_sock:
                client_sock, addr = sock.accept()
                socks.append(client_sock)
                print("Client<%s:%s> connected." % addr)
                broadcast_to_client(client_sock, "[%s:%s] joined. \n" % addr)
            else:
                try:
                    message_from_client = sock.recv(1024)
                    if message_from_client:
                        print("<%s>said: %s" % (str(sock.getpeername()), message_from_client))
                        broadcast_to_client(sock, "<%s>%s" % (str(sock.getpeername()), message_from_client))
                    else:
                        print("Client<%s:%s> left." % addr)
                        broadcast_to_client(sock, "Client<%s:%s> left." % addr)
                        sock.close()
                        socks.remove(sock)

                except Exception, e:
                    print(str(e))
                    broadcast_to_client(sock, "Client<%s:%s> left." % addr)
                    print("Client<%s:%s> left." % addr)
                    sock.close()
                    socks.remove(sock)
                    continue

    server_sock.close()

