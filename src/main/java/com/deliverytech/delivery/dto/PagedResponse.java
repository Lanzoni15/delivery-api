package com.deliverytech.delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedResponse<T> {
    private List<T> content;
    private PageMetadata page;
    private Map<String, String> links;

    public PagedResponse() {}
    public PagedResponse(List<T> content, PageMetadata page, Map<String, String> links) {
        this.content = content; this.page = page; this.links = links;
    }

    public List<T> getContent() { return content; }
    public void setContent(List<T> content) { this.content = content; }
    public PageMetadata getPage() { return page; }
    public void setPage(PageMetadata page) { this.page = page; }
    public Map<String, String> getLinks() { return links; }
    public void setLinks(Map<String, String> links) { this.links = links; }

    public static PageMetadata pageMetadata(int number, int size, long totalElements) {
        int totalPages = size == 0 ? 0 : (int)Math.ceil((double) totalElements / size);
        return new PageMetadata(number, size, totalElements, totalPages);
    }
}
