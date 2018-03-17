# -*- coding: utf-8 -*-
import socket

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(('127.0.0.1', 9090))
s.listen(5)
print 'serve is waiting connect......'

while True:
    sock, addr = s.accept()
    data = sock.recv(1024)
    print data
    sock.send("Hello, I am server.")

# 这是一个服务器雏形，开放在127.0.0.1:9090，对访问者发送一句Hello
