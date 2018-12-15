#!/bin/bash
# Script that creates database backup and then restores it
cd CSCE\ 361
mysqldump -u nlawrence -p nlawrence --opt --databases nlawrence > Scheduler_Backup.dump
mysql -u nlawrence -p nlawrence < Scheduler_Backup.dump