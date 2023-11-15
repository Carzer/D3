#!/bin/bash
set -e

echo -e "\033[32m start ssh \033[0m"
service ssh start
echo -e "\033[32m start journalnode \033[0m"
hdfs --daemon start journalnode
### 初次启动后需要注释掉 start
echo -e "\033[32m format namenode \033[0m"
$HADOOP_HOME/bin/hdfs namenode -format
echo -e "\033[32m format zkfc \033[0m"
$HADOOP_HOME/bin/hdfs zkfc -formatZK
### 初次启动后需要注释掉 end
echo -e "\033[32m start namenode \033[0m"
hdfs --daemon start namenode
echo -e "\033[32m start datanode \033[0m"
hdfs --daemon start datanode
echo -e "\033[32m start dfs \033[0m"
$HADOOP_HOME/sbin/start-dfs.sh
echo -e "\033[32m start yarn \033[0m"
$HADOOP_HOME/sbin/start-yarn.sh
echo -e "\033[32m start zkfc \033[0m"
hdfs --daemon start zkfc
echo -e "\033[32m start hbase master \033[0m"
$HBASE_HOME/bin/hbase master start