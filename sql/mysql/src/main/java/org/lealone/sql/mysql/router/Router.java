/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lealone.sql.mysql.router;

import org.lealone.db.result.Result;
import org.lealone.sql.mysql.ddl.DefineStatement;
import org.lealone.sql.mysql.dml.Delete;
import org.lealone.sql.mysql.dml.Insert;
import org.lealone.sql.mysql.dml.Merge;
import org.lealone.sql.mysql.dml.Select;
import org.lealone.sql.mysql.dml.Update;

public interface Router {

    int executeDefineCommand(DefineStatement defineCommand);

    int executeInsert(Insert insert);

    int executeMerge(Merge merge);

    int executeDelete(Delete delete);

    int executeUpdate(Update update);

    Result executeSelect(Select select, int maxRows, boolean scrollable);
}
