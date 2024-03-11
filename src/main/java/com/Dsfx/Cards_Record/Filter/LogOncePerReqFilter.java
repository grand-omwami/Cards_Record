package com.Dsfx.Cards_Record.Filter;

import com.Dsfx.Cards_Record.Model.LogReqRes;

import com.Dsfx.Cards_Record.Repository.LoggingDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Slf4j
public class LogOncePerReqFilter extends OncePerRequestFilter {
    @Autowired
    LoggingDao loggingDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper);

        String requestBody = getValueAsString(cachingRequestWrapper.getContentAsByteArray(),cachingRequestWrapper.getCharacterEncoding());
        String responseBody = getValueAsString(cachingResponseWrapper.getContentAsByteArray(),cachingResponseWrapper.getCharacterEncoding());

        logReqRes(requestBody, responseBody,cachingRequestWrapper.getRequestURI(),cachingRequestWrapper.getMethod());

        cachingResponseWrapper.copyBodyToResponse();
    }

    private String getValueAsString(byte[] contentAsByteArray, String characterEncoding) {
        String dataAsString = "";
        try {
            dataAsString = new String(contentAsByteArray, characterEncoding);
        }catch (Exception e) {
            log.error("Exception occurred while converting byte into an array: {}",e.getMessage());
            e.printStackTrace();
        }
        return dataAsString;
    }

    @Async
    protected void logReqRes(String request, String response, String uri, String httpMethod) {
        LogReqRes logReqRes = new LogReqRes();
        logReqRes.setRequest(request);
        logReqRes.setResponse(response);
        logReqRes.setUri(uri);
        logReqRes.setHttpMethod(httpMethod);

        loggingDao.save(logReqRes);
    }

}
