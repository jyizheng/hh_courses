package jlogic;

/* ====================================================================
 * Copyright (c) 2003 Daniel F. Savarese.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        Daniel F. Savarese (http://www.savarese.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Daniel F. Savarese" and "Daniel Savarese" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission.  For written
 *    permission, please contact licensing at savarese.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL DANIEL F. SAVARESE BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 */
/* Function.java,v 1.3 2003/04/16 04:13:43 dfs Exp */

import java.util.*;

public class Function extends Term {

  private ArrayList arguments;

  public Function(String name) {
    super(name);
    arguments = new ArrayList();
  }

  public void addArgument(Term arg) {
    arguments.add(arg);
  }

  public Iterator arguments() {
    return arguments.iterator();
  }

  public Iterator elements() throws UnsupportedOperationException {
    return arguments();
  }

  public boolean hasElements() { return true; }

  public Atom substitute(Substitution sub) {
    Function f  = createFunction(getName());
    Iterator it = arguments();

    while(it.hasNext()) {
      Term term = (Term)it.next();
      f.addArgument((Term)term.substitute(sub));
    }

    return f;
  }

  public Function createFunction(String name) {
    return new Function(name);
  }

  public boolean equals(Object obj) {
    return (super.equals(obj) &&
            obj instanceof Function /*&&
            arguments.equals(((Function)obj).arguments)*/);
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    Iterator it = arguments();

    buffer.append(getName());
    buffer.append("(");

    while(it.hasNext()) {
      buffer.append(it.next().toString());

      if(it.hasNext())
        buffer.append(", ");
    }

    buffer.append(")");

    return buffer.toString();
  }

}

