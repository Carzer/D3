#!/bin/bash
set -e

service ssh start
echo -e "\033[32m wating for master start in 3m \033[0m"
# sleep 3m
 echo -e "\033[32m hadoop start \033[0m"
hdfs --daemon start journalnode
### 初次启动后需要注释掉 start
echo -e "\033[32m format namenode \033[0m"
$HADOOP_HOME/bin/hdfs namenode -bootstrapStandby
### 初次启动后需要注释掉 end
### 初次启动后需要打开注释 start
echo -e "\033[32m start namenode \033[0m"
#hdfs --daemon start namenode
### 初次启动后需要打开注释 end
echo -e "\033[32m start datanode \033[0m"
hdfs --daemon start datanode
echo -e "\033[32m start resourcemanager \033[0m"
yarn --daemon start resourcemanager
echo -e "\033[32m start zkfc \033[0m"
hdfs --daemon start zkfc
echo -e "\033[32m hbase backup start \033[0m"
# $HBASE_HOME/bin/local-master-backup.sh start 1
$HBASE_HOME/bin/hbase master start --backup
