package com.ondc.yugabyte_integration.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ondc.yugabyte_integration.Service.MapToJsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "payload")
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String messageId;
    private String transactionId;
    private String payloadId;
    private String flowId;
    @Enumerated(EnumType.STRING)
    private Action action;
    private String bppId;
    private String bapId;

    @Convert(converter = MapToJsonConverter.class)
    @Column(columnDefinition = "text")
    private Map<String, Object> jsonRequest;
    @Convert(converter = MapToJsonConverter.class)
    @Column(columnDefinition = "text")
    private Map<String, Object> jsonResponse;
    private Integer httpStatus;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id", referencedColumnName = "sessionId")
    @JsonIgnoreProperties({"payloads", "npType", "npId", "domain", "version", "sessionType", "sessionActive"})
    private SessionDetails sessionDetails;

    @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public enum Type {
        REQUEST,
        RESPONSE
    }

    public enum Action {
        SEARCH ("search"),
        ON_SEARCH("on_search"),
        SELECT("select"),
        ON_SELECT("on_select"),
        INIT("init"),
        ON_INIT("on_init"),
        CONFIRM("confirm"),
        ON_CONFIRM("on_confirm"),
        STATUS("status"),
        ON_STATUS("on_status"),
        CANCEL("cancel"),
        ON_CANCEL("on_cancel"),
        UPDATE("update"),
        ON_UPDATE("on_update");

        public final String action;

        private Action(String action) {
            this.action = action;
        }
    }

    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "message_id")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Column(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Column(name = "payload_id")
    public String getPayloadId() {
        return payloadId;
    }

    public void setPayloadId(String payloadId) {
        this.payloadId = payloadId;
    }

    @Column(name = "flowId")
    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    @Column(name = "action")
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Column(name = "jsonRequest")
    public Map<String, Object> getJsonRequest() {
        return jsonRequest;
    }

    public void setJsonRequest(Map<String, Object> jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

    @Column(name = "jsonResponse")
    public Map<String, Object> getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(Map<String, Object> jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    @Column(name = "bpp_id")
    public String getBppId() {
        return bppId;
    }

    public void setBppId(String bppId) {
        this.bppId = bppId;
    }

    @Column(name = "bap_id")
    public String getBapId() {
        return bapId;
    }

    public void setBapId(String bapId) {
        this.bapId = bapId;
    }

    @Column(name = "http_status")
    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(name = "session_detail_id")
    public SessionDetails getSessionDetails() {
        return sessionDetails;
    }

    public void setSessionDetails(SessionDetails sessionDetails) {
        this.sessionDetails = sessionDetails;
    }
}
