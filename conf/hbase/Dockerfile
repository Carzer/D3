# 使用已有的jdk8镜像作为基础
FROM openjdk:8-jdk-bullseye

# 设置时间
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# 工作目录
WORKDIR /root

# 安装openssh-server、net-tools、vim、dnsmasq
RUN apt-get update
RUN apt-get install -y openssh-server net-tools vim dnsmasq psmisc
RUN apt-get clean all

# 设置jdk相关环境变量
ENV JAVA_HOME /usr/local/openjdk-8
ENV CLASSPATH ${JAVA_HOME}/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH $PATH:${JAVA_HOME}/bin

# 安装hadoop
ADD hadoop-3.3.1.tar.gz /usr/local/
RUN ln -s /usr/local/hadoop-3.3.1 /usr/local/hadoop

# 设置hadoop环境变量
ENV HADOOP_HOME /usr/local/hadoop
ENV HADOOP_COMMON_HOME /usr/local/hadoop
ENV HADOOP_HDFS_HOME /usr/local/hadoop
ENV HADOOP_MAPRED_HOME /usr/local/hadoop
ENV HADOOP_YARN_HOME /usr/local/hadoop
ENV HADOOP_CONF_DIR /usr/local/hadoop/etc/hadoop
ENV PATH ${HADOOP_HOME}/bin:$PATH
# 设置hdfs环境变量
ENV HDFS_NAMENODE_USER=root
ENV HDFS_DATANODE_USER=root
ENV HDFS_SECONDARYNAMENODE_USER=root
ENV HDFS_JOURNALNODE_USER=root
ENV YARN_RESOURCEMANAGER_USER=root
ENV YARN_NODEMANAGER_USER=root

# 安装hbase
ADD hbase-2.4.9-bin.tar.gz /usr/local/
RUN ln -s /usr/local/hbase-2.4.9 /usr/local/hbase

# 设置hbase环境变量
ENV HBASE_HOME /usr/local/hbase
ENV PATH ${HBASE_HOME}/bin:$PATH

# 使用自定义配置文件替换默认配置文件
ADD ./tmp/hadoop-env.sh $HADOOP_HOME/etc/hadoop/
ADD ./tmp/core-site.xml $HADOOP_HOME/etc/hadoop/
ADD ./tmp/mapred-site.xml $HADOOP_HOME/etc/hadoop/
ADD ./tmp/hdfs-site.xml $HADOOP_HOME/etc/hadoop/
ADD ./tmp/slaves $HADOOP_HOME/etc/hadoop/

ADD ./tmp/hbase-env.sh $HBASE_HOME/conf/
ADD ./tmp/backup-masters $HBASE_HOME/conf/
ADD ./tmp/regionservers $HBASE_HOME/conf/

# 创建hdfs相关目录
RUN mkdir -p ~/hdfs/namenode
RUN mkdir -p ~/hdfs/datanode
RUN mkdir -p ~/hdfs/journalNode

# 添加启动脚本及相关权限
RUN chmod 766 $HADOOP_HOME/sbin/start-dfs.sh
RUN chmod 766 $HADOOP_HOME/sbin/start-yarn.sh

# 配置公钥
RUN ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
RUN cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys 
# 配置免公钥免验证
ADD ./tmp/ssh_config /root/.ssh/config
RUN chmod 600 /root/.ssh/config
RUN chown root:root /root/.ssh/config
 
# 设置启动脚本
ENTRYPOINT ["/root/start.sh"]

