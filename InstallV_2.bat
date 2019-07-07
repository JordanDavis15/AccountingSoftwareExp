@ECHO off
SET tempPlace=C:\temp
SET destPlace=C:\Users\%USERNAME%\Documents

cd tempPlace
git clone https://github.com/JordanDavis15/AccountingSoftwareExp.git

xcopy build "%destPlace%\AccountingSoftwareExp" /s
IF EXIST "destPlace\AccountingSoftwareExp\acountingData.txt" ECHO data already exists...will not overwrite
ELSE xcopy accountingData.txt "%destPlace%\AccountingSoftwareExp" /s