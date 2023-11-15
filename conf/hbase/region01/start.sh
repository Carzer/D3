#!/bin/bash
set -e

service ssh start

echo -e "\033[32m hadoop start \033[0m"
hdfs --daemon start journalnode
echo -e "\033[32m hbase region start \033[0m"
$HBASE_HOME/bin/hbase regionserver start
