package com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain;

import com.sit.core.common.domain.CommonSQLPath;
import com.sit.smartcertificate.sendmailnotice.sql.TestSQL;

public enum SQLPath {

	TEST_SQL(TestSQL.class, "com/sit/smartcertificate/sendmailnotice/sql/Test.sql");
	;
	
	private ReferenceSQLPath sqlPath;

	private SQLPath(Class<?> className, String path) {
		this.sqlPath = new ReferenceSQLPath(className, path);
	}

	public CommonSQLPath getSqlPath() {
		return sqlPath;
	}

	public String getPath() {
		return sqlPath.getPath();
	}
}
