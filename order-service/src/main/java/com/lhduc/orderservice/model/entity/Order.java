package com.lhduc.orderservice.model.entity;

import com.lhduc.orderservice.enums.PaymentStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

import static com.lhduc.orderservice.constant.DatabaseConstant.CREATED_AT_COLUMN_NAME;
import static com.lhduc.orderservice.constant.DatabaseConstant.ORDER_TABLE_NAME;
import static com.lhduc.orderservice.constant.DatabaseConstant.UPDATED_AT_COLUMN_NAME;

@Entity
@Table(name = ORDER_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {
    private static final String PAYMENT_STATUS_COLUMN_NAME = "payment_status";
    private static final String ORDER_NUMBER_COLUMN_NAME = "order_number";
    private static final String ORDER_MAPPING = "order";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = ORDER_NUMBER_COLUMN_NAME)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = PAYMENT_STATUS_COLUMN_NAME, nullable = false)
    private PaymentStatus paymentStatus;

    @CreationTimestamp
    @Column(name = CREATED_AT_COLUMN_NAME)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = UPDATED_AT_COLUMN_NAME)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = ORDER_MAPPING, cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItems;
}
