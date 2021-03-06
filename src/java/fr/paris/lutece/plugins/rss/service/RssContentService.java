/*
 * Copyright (c) 2002-2014, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.rss.service;

import fr.paris.lutece.portal.service.resource.ResourceService;
import fr.paris.lutece.portal.service.util.AppLogService;


/**
 * RSS Content service
 */
public final class RssContentService extends ResourceService
{
    private static final String PROPERTY_LOADER = "rss.content.service.loaders";
    private static final String PROPERTY_NAME = "rss.content.service.name";
    private static final String PROPERTY_CACHE = "rss.content.service.cache";
    private static RssContentService _singleton = new RssContentService(  );

    /**
     * Creates a new instance of RssContentService
     */
    private RssContentService(  )
    {
        setNameKey( PROPERTY_NAME );
        setCacheKey( PROPERTY_CACHE );
    }

    /**
     * Returns the unique instance
     * @return the unique instance
     */
    public static RssContentService getInstance(  )
    {
        return _singleton;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected String getLoadersProperty(  )
    {
        return PROPERTY_LOADER;
    }

    /**
     * Gets the RSS content
     * @param strId The RSS id
     * @return The content
     */
    public RssContent getRssContent( String strId )
    {
        return (RssContent) getResource( strId );
    }

    /**
     * Gets the RSS content
     * @param nIdRssFeed The RSS id
     * @return The content
     */
    public String getRssContent( int nIdRssFeed )
    {
        String rssContent = "";

        try
        {
            rssContent = RssContentLoader.fetchRssFeed( nIdRssFeed );
        }
        catch ( RssParsingException e )
        {
            AppLogService.error( e.getMessage(  ) );
        }

        return rssContent;
    }
}
