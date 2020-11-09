package com.lambda.orders.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order
{
    /*
        ORDNUM primary key, not null Long
        ORDAMOUNT double
        ADVANCEAMOUNT double
        CUSTCODE Long foreign key (one customer to many orders) not null
        ORDERDESCRIPTION String
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;

    private double ordamount;
    private double advanceamount;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    private Customer customer;

    private String orderdescription;

    // Connect Order to Payment
    @ManyToMany
    @JoinTable(name = "orderpayments",
             joinColumns = @JoinColumn(name = "ordernum"),
             inverseJoinColumns = @JoinColumn(name = "pyamentid")
    )
    // With many to many you want to use sets bc it will enforce uniqueness
    private Set<Payment> payments = new HashSet<>();

    // JPA Constructor
    public Order(){}

    // Normal Constructor
    public Order(double ordamount, double advanceamount, Customer custcode, String orderdescription)
    {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.customer = custcode;
        this.orderdescription = orderdescription;
    }

    // Getters and setters

    public long getOrdnum()
    {
        return ordnum;
    }

    public void setOrdnum(long ordnum)
    {
        this.ordnum = ordnum;
    }

    public double getOrdamount()
    {
        return ordamount;
    }

    public void setOrdamount(double ordamount)
    {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount()
    {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount)
    {
        this.advanceamount = advanceamount;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer custcode)
    {
        this.customer = custcode;
    }

    public String getOrderdescription()
    {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription)
    {
        this.orderdescription = orderdescription;
    }

    public Set<Payment> getPayments()
    {
        return payments;
    }

    public void setPayments(Set<Payment> payments)
    {
        this.payments = payments;
    }
}
