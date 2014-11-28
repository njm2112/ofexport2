/*
Copyright 2014 Paul Sidnell

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.psidnell.omnifocus.format;

import java.io.StringWriter;

import org.junit.Test;
import org.psidnell.omnifocus.OFExport;
import org.psidnell.omnifocus.integrationtest.Diff;

public class XMLTest extends FormatTest {
    
    @Test
    public void testProjectMode () throws Exception {
        OFExport ofExport = new OFExport();
        ofExport.getProjectRoot().add(f1);
        ofExport.setFormat("XML");
        ofExport.process();
        StringWriter out = new StringWriter();
        ofExport.write(out);
        
        System.out.println(out);
        
        Diff.diff (new String[]
            {
                "<Folder>",
                "  <name>RootFolder</name>",
                "  <id>__%%RootFolder</id>",
                "  <rank>0</rank>",
                "  <projects/>",
                "  <folders>",
                "    <folders>",
                "      <name>f1</name>",
                "      <id>%%%%f1</id>",
                "      <rank>0</rank>",
                "      <projects>",
                "        <projects>",
                "          <name>p1</name>",
                "          <id>%%%%p1</id>",
                "          <rank>0</rank>",
                "          <note/>",
                "          <deferDate/>",
                "          <dueDate/>",
                "          <completionDate/>",
                "          <sequential>false</sequential>",
                "          <flagged>false</flagged>",
                "          <tasks>",
                "            <tasks>",
                "              <name>t1</name>",
                "              <id>%%%%t1</id>",
                "              <rank>0</rank>",
                "              <note/>",
                "              <deferDate>1417046400000</deferDate>",
                "              <dueDate/>",
                "              <completionDate/>",
                "              <sequential>false</sequential>",
                "              <flagged>true</flagged>",
                "              <tasks/>",
                "              <parentTaskId/>",
                "              <contextId/>",
                "              <blocked>false</blocked>",
                "              <available>true</available>",
                "              <remaining>true</remaining>",
                "            </tasks>",
                "            <tasks>",
                "              <name>t2</name>",
                "              <id>%%%%t2</id>",
                "              <rank>0</rank>",
                "              <note>line1",
                "line2</note>",
                "              <deferDate/>",
                "              <dueDate>1417046400000</dueDate>",
                "              <completionDate/>",
                "              <sequential>false</sequential>",
                "              <flagged>false</flagged>",
                "              <tasks/>",
                "              <parentTaskId/>",
                "              <contextId/>",
                "              <blocked>false</blocked>",
                "              <available>true</available>",
                "              <remaining>true</remaining>",
                "            </tasks>",
                "            <tasks>",
                "              <name>t3</name>",
                "              <id>%%%%t3</id>",
                "              <rank>0</rank>",
                "              <note>line1",
                "line2</note>",
                "              <deferDate/>",
                "              <dueDate/>",
                "              <completionDate>1417046400000</completionDate>",
                "              <sequential>false</sequential>",
                "              <flagged>false</flagged>",
                "              <tasks>",
                "                <tasks>",
                "                  <name>t4</name>",
                "                  <id>%%%%t4</id>",
                "                  <rank>0</rank>",
                "                  <note>line1",
                "line2</note>",
                "                  <deferDate/>",
                "                  <dueDate/>",
                "                  <completionDate>1417046400000</completionDate>",
                "                  <sequential>false</sequential>",
                "                  <flagged>false</flagged>",
                "                  <tasks/>",
                "                  <parentTaskId/>",
                "                  <contextId/>",
                "                  <blocked>false</blocked>",
                "                  <available>false</available>",
                "                  <remaining>false</remaining>",
                "                </tasks>",
                "              </tasks>",
                "              <parentTaskId/>",
                "              <contextId/>",
                "              <blocked>false</blocked>",
                "              <available>false</available>",
                "              <remaining>false</remaining>",
                "            </tasks>",
                "          </tasks>",
                "          <status/>",
                "          <available>false</available>",
                "          <remaining>true</remaining>",
                "        </projects>",
                "        <projects>",
                "          <name>p2</name>",
                "          <id>%%%%p2</id>",
                "          <rank>0</rank>",
                "          <note>line1",
                "line2</note>",
                "          <deferDate/>",
                "          <dueDate>1417046400000</dueDate>",
                "          <completionDate>1417046400000</completionDate>",
                "          <sequential>false</sequential>",
                "          <flagged>true</flagged>",
                "          <tasks/>",
                "          <status/>",
                "          <available>false</available>",
                "          <remaining>false</remaining>",
                "        </projects>",
                "        <projects>",
                "          <name>p3</name>",
                "          <id>%%%%p3</id>",
                "          <rank>0</rank>",
                "          <note/>",
                "          <deferDate/>",
                "          <dueDate/>",
                "          <completionDate/>",
                "          <sequential>false</sequential>",
                "          <flagged>false</flagged>",
                "          <tasks/>",
                "          <status/>",
                "          <available>false</available>",
                "          <remaining>true</remaining>",
                "        </projects>",
                "      </projects>",
                "      <folders/>",
                "      <parentFolderId/>",
                "    </folders>",
                "  </folders>",
                "  <parentFolderId/>",
                "</Folder>",
            }, out.toString().split("\n"));
    }
    
    @Test
    public void testContextMode () throws Exception {
        OFExport ofExport = new OFExport();
        ofExport.setProjectMode(false);
        ofExport.getContextRoot().add(c1);
        ofExport.setFormat("XML");
        ofExport.process();
        StringWriter out = new StringWriter();
        ofExport.write(out);
        
        System.out.println(out);
        
        Diff.diff (new String[]
            {
                "<Context>",
                "  <name>RootContext</name>",
                "  <id>__%%RootContext</id>",
                "  <rank>0</rank>",
                "  <tasks/>",
                "  <contexts>",
                "    <contexts>",
                "      <name>c1</name>",
                "      <id>%%%%c1</id>",
                "      <rank>0</rank>",
                "      <tasks>",
                "        <tasks>",
                "          <name>t2</name>",
                "          <id>%%%%t2</id>",
                "          <rank>0</rank>",
                "          <note>line1",
                "line2</note>",
                "          <deferDate/>",
                "          <dueDate>1417046400000</dueDate>",
                "          <completionDate/>",
                "          <sequential>false</sequential>",
                "          <flagged>false</flagged>",
                "          <tasks/>",
                "          <parentTaskId/>",
                "          <contextId/>",
                "          <blocked>false</blocked>",
                "          <available>true</available>",
                "          <remaining>true</remaining>",
                "        </tasks>",
                "      </tasks>",
                "      <contexts>",
                "        <contexts>",
                "          <name>c2</name>",
                "          <id>%%%%c2</id>",
                "          <rank>0</rank>",
                "          <tasks>",
                "            <tasks>",
                "              <name>t3</name>",
                "              <id>%%%%t3</id>",
                "              <rank>0</rank>",
                "              <note>line1",
                "line2</note>",
                "              <deferDate/>",
                "              <dueDate/>",
                "              <completionDate>1417046400000</completionDate>",
                "              <sequential>false</sequential>",
                "              <flagged>false</flagged>",
                "              <tasks>",
                "                <tasks>",
                "                  <name>t4</name>",
                "                  <id>%%%%t4</id>",
                "                  <rank>0</rank>",
                "                  <note>line1",
                "line2</note>",
                "                  <deferDate/>",
                "                  <dueDate/>",
                "                  <completionDate>1417046400000</completionDate>",
                "                  <sequential>false</sequential>",
                "                  <flagged>false</flagged>",
                "                  <tasks/>",
                "                  <parentTaskId/>",
                "                  <contextId/>",
                "                  <blocked>false</blocked>",
                "                  <available>false</available>",
                "                  <remaining>false</remaining>",
                "                </tasks>",
                "              </tasks>",
                "              <parentTaskId/>",
                "              <contextId/>",
                "              <blocked>false</blocked>",
                "              <available>false</available>",
                "              <remaining>false</remaining>",
                "            </tasks>",
                "            <tasks>",
                "              <name>t4</name>",
                "              <id>%%%%t4</id>",
                "              <rank>0</rank>",
                "              <note>line1",
                "line2</note>",
                "              <deferDate/>",
                "              <dueDate/>",
                "              <completionDate>1417046400000</completionDate>",
                "              <sequential>false</sequential>",
                "              <flagged>false</flagged>",
                "              <tasks/>",
                "              <parentTaskId/>",
                "              <contextId/>",
                "              <blocked>false</blocked>",
                "              <available>false</available>",
                "              <remaining>false</remaining>",
                "            </tasks>",
                "          </tasks>",
                "          <contexts/>",
                "          <parentContextId/>",
                "          <active>false</active>",
                "          <allowsNextAction>false</allowsNextAction>",
                "        </contexts>",
                "      </contexts>",
                "      <parentContextId/>",
                "      <active>false</active>",
                "      <allowsNextAction>false</allowsNextAction>",
                "    </contexts>",
                "  </contexts>",
                "  <parentContextId/>",
                "  <active>false</active>",
                "  <allowsNextAction>false</allowsNextAction>",
                "</Context>",
            }, out.toString().split("\n"));
    }
}
