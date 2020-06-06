Parameters to pass when executing QueueSend: 

--url (tcp://192.168.56.14:61616,tcp://192.168.56.12:61626,tcp://192.168.56.12:61616,tcp://192.168.56.16:61626,tcp://192.168.56.16:61616,tcp://192.168.56.14:61626)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0 --user amqadm --password amqadm --queue mytest-queue-b17 --message-size 1000 --message-count 10 --thread-size 1



http://35.247.171.145/

java -cp AmqDemo.jar com.demo.AmqDemo.App --url (tcp://35.247.171.145:61616,tcp://35.247.171.145:61626,tcp://35.247.129.110:61616,tcp://35.247.129.110:61626,tcp://35.240.165.214:61616)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0 --user amqadm --password amqadm --queue mytest-queue-b1 --message-size 1000 --message-count 10 --thread-size 1

java -cp AmqDemo-jar-with-dependencies.jar:AmqDemo.jar com.demo.AmqDemo.App --url (tcp:/35.247.171.145:61616)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0 --user amqadm --password amqadm --queue mytest-queue-b1 --message-size 1000 --message-count 10 --thread-size 1
(tcp:/35.247.171.145:61616)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0



tcp://35.247.171.145:61616,tcp://35.247.171.145:61626,tcp://35.247.129.110:61616,tcp://35.247.129.110:61626,tcp://35.240.165.214:61616,tcp://35.240.165.214:61626

1a - http://35.247.171.145:8161/console
3b - http://35.247.171.145:8162/console

2a - http://35.247.129.110:8161/console
1b - http://35.247.129.110:8162/console


3a - http://35.240.165.214:8161/console
2b - http://35.240.165.214:8162/console