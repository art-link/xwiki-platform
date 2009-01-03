/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.xpn.xwiki.wysiwyg.server.cleaner.internal;

import java.io.StringReader;

import org.w3c.dom.Document;
import org.xwiki.xml.XMLUtils;

import com.xpn.xwiki.web.Utils;
import com.xpn.xwiki.wysiwyg.server.cleaner.HTMLCleaner;

/**
 * Default HTML cleaner for the WYSIWYG editor's output.
 * 
 * @version $Id$
 */
public class DefaultHTMLCleaner implements HTMLCleaner
{
    /**
     * Filter used to remove or convert the HTML elements that were added by the WYSIWYG editor only for internal
     * reasons.
     */
    private final WysiwygCleaningFilter filter = new WysiwygCleaningFilter();

    /**
     * {@inheritDoc}
     * 
     * @see HTMLCleaner#clean(String)
     */
    public String clean(String dirtyHTML)
    {
        org.xwiki.xml.html.HTMLCleaner cleaner =
            (org.xwiki.xml.html.HTMLCleaner) Utils.getComponent(org.xwiki.xml.html.HTMLCleaner.ROLE);
        Document document = cleaner.clean(new StringReader(dirtyHTML));
        filter.filter(document);
        return XMLUtils.toString(document);
    }
}
