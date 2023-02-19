CREATE TABLE process_def (
	process_id serial PRIMARY KEY,
	name VARCHAR ( 50 ) UNIQUE NOT NULL
);

CREATE TABLE step_def (
	step_id serial PRIMARY KEY,
	name VARCHAR ( 50 ) UNIQUE NOT NULL,
	process_id INT REFERENCES process_def(process_id),
	approver_role VARCHAR ( 50 ),
	is_initial_step boolean,
	is_terminal_step boolean
);


CREATE TABLE step_transition (
    id serial PRIMARY KEY,
	process_id INT REFERENCES process_def(process_id),
	current_step_id INT REFERENCES step_def(step_id),
	next_step_id INT REFERENCES step_def(step_id),
	action VARCHAR ( 50 )
);

CREATE TABLE app_user (
	user_id serial PRIMARY KEY,
	name VARCHAR ( 50 ),
	role VARCHAR ( 50 ),
	city VARCHAR ( 50 )
);

CREATE TABLE approver (
    id serial PRIMARY KEY,
	user_id INT REFERENCES app_user(user_id),
	approver_role VARCHAR ( 50 ),
	city VARCHAR ( 50 )
);

Create table leave(
    request_id serial PRIMARY KEY,
    number_Of_days Int,
    requested_by_user_id Int REFERENCES app_user(user_id),
    status VARCHAR ( 50 )
);

Create table leave_step(
    step_id serial PRIMARY KEY,
    step_def_id INT,
    leave_request_id INT REFERENCES leave(request_id),
    status VARCHAR ( 50 ),
    remark VARCHAR ( 50 ),
    approver_id INT REFERENCES app_user(user_id)
);
