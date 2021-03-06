/******************************************************************************* 
 * Copyright (c) 2015 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 * Tomas Milata - Added Batch diagram editor (JBIDE-19717).
 ******************************************************************************/ 
package org.jboss.tools.batch.ui.itest;

import org.eclipse.sapphire.ElementList;
import org.eclipse.sapphire.ui.forms.swt.MasterDetailsEditorPage;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.jboss.tools.batch.ui.editor.internal.model.BatchletOrChunk;
import org.jboss.tools.batch.ui.editor.internal.model.Chunk;
import org.jboss.tools.batch.ui.editor.internal.model.FlowElement;
import org.jboss.tools.batch.ui.editor.internal.model.Job;
import org.jboss.tools.batch.ui.editor.internal.model.JobXMLEditor;
import org.jboss.tools.batch.ui.editor.internal.model.Step;

/**
 * @author 
 */
public class BatchEditorInPlainProjectTest extends AbstractBatchSapphireEditorTest {

	protected String getProjectName() {
		return "PlainTestProject";
	}

	public void testEditor() {
		 editor = openEditor("job.xml");
		 JobXMLEditor jobEditor = (JobXMLEditor)editor;

		 Job job = jobEditor.getSchema();
		 assertNotNull(job);
		 ElementList<FlowElement> es = job.getFlowElements();
		 assertEquals(1, es.size());
		 Step step = (Step)es.get(0);
		 ElementList<BatchletOrChunk> ch = step.getBatchletOrChunk();
		 Chunk chunk = (Chunk) (ch.iterator().next());
		 
		 assertEquals("myReader", chunk.getReader().getRef().content());
		 assertEquals("myItemWriter", chunk.getWriter().getRef().content());
		 assertEquals("myItemProcessor", chunk.getProcessor().content().getRef().content());

		 StructuredTextEditor textEditor = jobEditor.getSourceEditor();
		 assertNotNull(textEditor);

		 MasterDetailsEditorPage formEditor = jobEditor.getFormEditor();
		 assertNotNull(formEditor);
	}

}
