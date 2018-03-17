# -*- coding: cp936 -*-
import threading, sys, select
import time
import socket

# 一个客户端，用于接入聊天服务器

def read_handler(sock):
    inputs = [sock]
    while True:
        r_socks, w_socks, e_socks = select.select(inputs, [], [])
        if sock in r_socks:
            data = sock.recv(1024)

            if not data:
                print("\nDisconnnected from server. Try to reconnect.")
                sys.exit()
            else:
                print("\n" + data)


def send_handler(sock):
    while True:
        data = raw_input()
        sock.send(data)


if __name__ == "__main__":

    if len(sys.argv) < 3:
        print "Usage: python client.py ip port"
        sys.exit()

    ip = sys.argv[1]
    port = int(sys.argv[2])

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # s.settimeout(2)

    try:
        s.connect((ip, port))
    except Exception, e:
        print("Connect Error")
        print(str(e))
        sys.exit()

    print("Connection's established. Chat now!")

    t = threading.Thread(target=read_handler, args=(s, ))
    t.start()

    t1 = threading.Thread(target=send_handler, args=(s,))
    t1.start()

    # while True:
    #     data = raw_input("please input:")
    #     s.send(data)
    #     print("send data" + data)
