FROM tomcat:9

# 压缩文件解压路径
# ENV RUN_PREFIX  /opt

ENV LC_ALL en_US.UTF-8
ENV webapps /usr/local/tomcat/webapps

EXPOSE 8080

RUN rm -rf ${webapps}/ROOT/
ADD MicroService_GasolineDissolution-1.0-SNAPSHOT.war ${webapps}/ROOT/
RUN unzip ${webapps}/ROOT/MicroService_GasolineDissolution-1.0-SNAPSHOT.war -d ${webapps}/ROOT/ && echo Asia/Shanghai >> /etc/timezone
CMD ["catalina.sh","run"]



# APP名字
# ENV APP_NAME unknown_ms_gas



# 启动命令
# CMD ["sh", "-c", "./bin/${APP_NAME}"]