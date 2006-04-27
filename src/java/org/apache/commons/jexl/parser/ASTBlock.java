/*
 * Copyright 2002-2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.jexl.parser;

import org.apache.commons.jexl.JexlContext;

/**
 * { code }, a block of statements enclosed in curly braces.
 * 
 * @author Dion Gillard
 * 
 */
public class ASTBlock extends SimpleNode {
    public ASTBlock(int id) {
        super(id);
    }

    public ASTBlock(Parser p, int id) {
        super(p, id);
    }

    /** {@inheritDoc} */
    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    /**
     * @return the value of the block. Execute all statements inside and return
     *         the value of the last.
     */
    public Object value(JexlContext context) throws Exception {
        int numChildren = jjtGetNumChildren();
        Object result = null;
        for (int i = 0; i < numChildren; i++) {
            result = ((SimpleNode) jjtGetChild(i)).value(context);
        }
        return result;
    }

}
