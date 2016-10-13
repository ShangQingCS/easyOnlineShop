-- Create the user 
create user "DEV"
  identified by "dev"
  default tablespace USERS
  temporary tablespace TEMP
  profile DEFAULT;

-- Grant/Revoke role privileges 
grant resource to "DEV";
grant connect to "DEV";
grant exp_full_database to "DEV";
grant imp_full_database to "DEV";
GRANT UNLIMITED TABLESPACE TO "DEV";