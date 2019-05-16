@echo off
SET /P inputname= Enter path for installation: 
xcopy build "%inputname%\AccountingSoftwareExp" /s

echo cd "%inputname%" >Run.bat
echo java -cp %inputname%\AccountingSoftwareExp\classes accounting_software_view.AccountingSoftwareExp >> Run.bat

REM installHELPER.bat
REM echo cd "c:\Users\%username%\Desktop" >Run.bat
REM echo java -cp %inputname%\AccountingSoftwareExp\classes accounting_software_view.AccountingSoftwareExp >> Run.bat
pause
