-- 내 스키마
DROP SCHEMA IF EXISTS erp_jsp_exam;

-- 내 스키마
CREATE SCHEMA erp_jsp_exam;

-- 직책
CREATE TABLE erp_jsp_exam.title (
	title_No   INT         NOT NULL COMMENT '직책코드', -- 직책코드
	title_Name VARCHAR(10) NOT NULL COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE erp_jsp_exam.title
	ADD CONSTRAINT PK_title -- 직책 기본키
		PRIMARY KEY (
			title_No -- 직책코드
		);

-- 부서
CREATE TABLE erp_jsp_exam.department (
	dept_No   INT         NOT NULL COMMENT '부서번호', -- 부서번호
	dept_Name VARCHAR(20) NOT NULL COMMENT '부서명', -- 부서명
	floor    INT          NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE erp_jsp_exam.department
	ADD CONSTRAINT PK_department -- 부서 기본키
		PRIMARY KEY (
			dept_No -- 부서번호
		);

-- 사원
CREATE TABLE erp_jsp_exam.employee (
	emp_no    INT         NOT NULL COMMENT '사원번호', -- 사원번호
	emp_name  VARCHAR(20) NOT NULL COMMENT '사원명', -- 사원명
	tno       INT         NULL     COMMENT '직책', -- 직책
	manager   INT         NULL     COMMENT '직속상사', -- 직속상사
	salary    INT         NULL     COMMENT '급여번호', -- 급여번호
	dno       INT         NULL     COMMENT '부서', -- 부서
	hiredate  date		  NULL     COMMENT '입사일' -- 입사일
)
COMMENT '사원';

-- 사원
ALTER TABLE erp_jsp_exam.employee
	ADD CONSTRAINT PK_employee -- 사원 기본키
		PRIMARY KEY (
			emp_no -- 사원번호
		);

-- 사원
ALTER TABLE erp_jsp_exam.employee
	ADD CONSTRAINT FK_title_TO_employee -- 직책 -> 사원
		FOREIGN KEY (
			tno -- 직책
		)
		REFERENCES erp_jsp_exam.title ( -- 직책
			title_No -- 직책코드
		);

-- 사원
ALTER TABLE erp_jsp_exam.employee
	ADD CONSTRAINT FK_employee_TO_employee -- 사원 -> 사원
		FOREIGN KEY (
			manager -- 직속상사
		)
		REFERENCES erp_jsp_exam.employee ( -- 사원
			emp_no -- 사원번호
		);

-- 사원
ALTER TABLE erp_jsp_exam.employee
	ADD CONSTRAINT FK_department_TO_employee -- 부서 -> 사원
		FOREIGN KEY (
			dno    -- 부서
		)
		REFERENCES erp_jsp_exam.department ( -- 부서
			dept_No -- 부서번호
		);
		
	
	
	
	
	
	
	
	
	
	