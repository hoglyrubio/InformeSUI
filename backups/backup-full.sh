#!/bin/bash
NOW=`date +%Y%m%d-%H%M`
FILE="backup-$NOW-full.sql"
TARGZ="backup-$NOW-full.tar.gz"
echo "backup full to $FILE"
/usr/local/mysql-5.5.25a-osx10.6-x86_64/bin/mysqldump -u root -p --routines empprivserv > $FILE
tar zcvf $TARGZ $FILE 