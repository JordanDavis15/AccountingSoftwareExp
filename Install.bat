@echo off
SET /P inputname= Enter path for installation(ex. C:\Program Files): 
xcopy build "%inputname%\AccountingSoftwareExp" /s
xcopy accountingData.txt "%inputname%\AccountingSoftwareExp" /s

echo cd "%inputname%\AccountingSoftwareExp" >Run.bat
echo java -cp %inputname%\AccountingSoftwareExp\classes accounting_software_view.AccountingSoftwareExp >> Run.bat

pause
