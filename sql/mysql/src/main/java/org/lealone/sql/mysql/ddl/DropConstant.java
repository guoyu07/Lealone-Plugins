/*
 * Copyright 2004-2013 H2 Group. Multiple-Licensed under the H2 License,
 * Version 1.0, and under the Eclipse Public License, Version 1.0
 * (http://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.lealone.sql.mysql.ddl;

import org.lealone.api.ErrorCode;
import org.lealone.common.exceptions.DbException;
import org.lealone.db.Database;
import org.lealone.db.ServerSession;
import org.lealone.db.schema.Constant;
import org.lealone.db.schema.Schema;
import org.lealone.sql.SQLStatement;

/**
 * This class represents the statement
 * DROP CONSTANT
 */
public class DropConstant extends SchemaStatement {

    private String constantName;
    private boolean ifExists;

    public DropConstant(ServerSession session, Schema schema) {
        super(session, schema);
    }

    public void setIfExists(boolean b) {
        ifExists = b;
    }

    public void setConstantName(String constantName) {
        this.constantName = constantName;
    }

    @Override
    public int update() {
        session.getUser().checkAdmin();
        session.commit(true);
        Database db = session.getDatabase();
        Constant constant = getSchema().findConstant(constantName);
        if (constant == null) {
            if (!ifExists) {
                throw DbException.get(ErrorCode.CONSTANT_NOT_FOUND_1, constantName);
            }
        } else {
            db.removeSchemaObject(session, constant);
        }
        return 0;
    }

    @Override
    public int getType() {
        return SQLStatement.DROP_CONSTANT;
    }

}
