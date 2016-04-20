#!/bin/bash
NOW=`date +%Y%m%d-%H%M`
FILE="backup-$NOW-data.sql"
TARGZ="backup-$NOW-data.tar.gz"
echo "backup data to $FILE"
/usr/local/mysql-5.5.25a-osx10.6-x86_64/bin/mysqldump -u root -p --no-create-info --skip-triggers empprivserv > $FILE
tar zcvf $TARGZ $FILE 