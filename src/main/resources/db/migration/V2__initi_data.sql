Insert into app_user values (1, 'John Doe Pune', 'PUNE_MANAGER', 'PUNE');
Insert into app_user values (2, 'Prakash Doe Mumbai', 'MUMBAI_MANAGER', 'MUMBAI');
Insert into app_user values (3, 'Deepak Doe MH', 'MH_MANAGER', 'NAGPUR');
Insert into app_user values (4, 'Jane Doe Pune Employee', 'EMPLOYEE', 'PUNE');
Insert into app_user values (5, 'Rahul Doe Mumbai Employee', 'EMPLOYEE', 'MUMBAI');

Insert into process_def values (1, 'LEAVE_APPLICATION');

Insert into step_def values (1, 'LA_INITIATED', 1, 'CITY_MANAGER', true, false);
Insert into step_def values (2, 'LA_STATE_MANAGER_APPROVAL', 1, 'STATE_MANAGER', false, false);
Insert into step_def values (3, 'LA_APPROVED', 1, null, false, true);

insert into step_transition values (1, 1, 1, 2, 'APPROVE');
insert into step_transition values (2, 1, 2, 3, 'APPROVE');


Insert into process_def values (2, 'EXPENSE_APPLICATION');

Insert into step_def values (4, 'EXP_INITIATED', 2, 'CITY_MANAGER', true, false);
Insert into step_def values (5, 'EXP_STATE_MANAGER_APPROVAL', 2, 'STATE_MANAGER', false, false);
Insert into step_def values (6, 'EXP_APPROVED', 2, null, false, true);
Insert into step_def values (7, 'EXP_REJECTED', 2, null, false, true);

insert into step_transition values (3, 2, 1, 2, 'APPROVE');
insert into step_transition values (4, 2, 2, 3, 'APPROVE');
insert into step_transition values (5, 2, 2, 7, 'REJECT');

insert into approver values (1, 1, 'CITY_MANAGER', 'PUNE');
insert into approver values (2, 2, 'CITY_MANAGER', 'MUMBAI');
insert into approver values (3, 3, 'STATE_MANAGER', 'PUNE');
insert into approver values (4, 3, 'STATE_MANAGER', 'MUMBAI');

