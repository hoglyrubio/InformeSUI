#!/bin/bash
NOW=`date +%Y%m%d-%H%M`
FILE="backup-$NOW-schema.sql"
TARGZ="backup-$NOW-schema.tar.gz"
echo "backup schema to $FILE"
/usr/local/mysql-5.5.25a-osx10.6-x86_64/bin/mysqldump -u root -p --no-data --routines empprivserv > $FILE
tar zcvf $TARGZ $FILE