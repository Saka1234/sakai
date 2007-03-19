/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2007 The Sakai Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/
package org.sakaiproject.sitemanage.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.sitemanage.api.SectionField;
import org.sakaiproject.sitemanage.api.SectionFieldManager;
import org.sakaiproject.util.ResourceLoader;

public class SectionFieldManagerImpl implements SectionFieldManager {
	private static final Log log = LogFactory.getLog(SectionFieldManagerImpl.class);
	protected static final List<SectionField> fieldList = new ArrayList<SectionField>();
	protected static final ResourceLoader resourceLoader;
	
	static {
		resourceLoader = new ResourceLoader("SectionFields");

		fieldList.add(new SectionFieldImpl(resourceLoader.getString("required_fields_subject"), null, 8));
		fieldList.add(new SectionFieldImpl(resourceLoader.getString("required_fields_course"), null, 3));
		fieldList.add(new SectionFieldImpl(resourceLoader.getString("required_fields_section"), null, 3));		
	}

	public List<SectionField> getRequiredFields() {
		return fieldList;
	}

	public String getSectionEid(String academicSessionEid, List<SectionField> fields) {
		if(fields == null || fields.isEmpty()) {
			if(log.isDebugEnabled()) log.debug("Returning an empty sectionEID for an empty (or null) list of fields");
			return "";
		}
		
		String[] values = new String[fields.size()+1];
		for(int i = 0; i < fields.size(); i++) {
			SectionField sf = fields.get(i);
			values[i] = sf.getValue();
		}
		values[fields.size()] = academicSessionEid;
		
		String sectionEid = resourceLoader.getFormattedMessage("section_eid", values);
		if(log.isDebugEnabled()) log.debug("Generated section eid = " + sectionEid);
		return sectionEid;
	}

	public void init() {
	}

	public void destroy() {
	}

}
