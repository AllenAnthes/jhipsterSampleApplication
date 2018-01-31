package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A PIN.
 */
@Entity
@Table(name = "pin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PIN implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_account")
    private String account;

    @Column(name = "pin")
    private String pin;

    @Column(name = "createip")
    private String createip;

    @Column(name = "createuser")
    private String createuser;

    @Column(name = "createtimestamp")
    private Instant createtimestamp;

    @Column(name = "expiretimestamp")
    private Instant expiretimestamp;

    @Column(name = "claimtimestamp")
    private Instant claimtimestamp;

    @Column(name = "claimuser")
    private String claimuser;

    @Column(name = "claimip")
    private String claimip;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public PIN account(String account) {
        this.account = account;
        return this;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPin() {
        return pin;
    }

    public PIN pin(String pin) {
        this.pin = pin;
        return this;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCreateip() {
        return createip;
    }

    public PIN createip(String createip) {
        this.createip = createip;
        return this;
    }

    public void setCreateip(String createip) {
        this.createip = createip;
    }

    public String getCreateuser() {
        return createuser;
    }

    public PIN createuser(String createuser) {
        this.createuser = createuser;
        return this;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Instant getCreatetimestamp() {
        return createtimestamp;
    }

    public PIN createtimestamp(Instant createtimestamp) {
        this.createtimestamp = createtimestamp;
        return this;
    }

    public void setCreatetimestamp(Instant createtimestamp) {
        this.createtimestamp = createtimestamp;
    }

    public Instant getExpiretimestamp() {
        return expiretimestamp;
    }

    public PIN expiretimestamp(Instant expiretimestamp) {
        this.expiretimestamp = expiretimestamp;
        return this;
    }

    public void setExpiretimestamp(Instant expiretimestamp) {
        this.expiretimestamp = expiretimestamp;
    }

    public Instant getClaimtimestamp() {
        return claimtimestamp;
    }

    public PIN claimtimestamp(Instant claimtimestamp) {
        this.claimtimestamp = claimtimestamp;
        return this;
    }

    public void setClaimtimestamp(Instant claimtimestamp) {
        this.claimtimestamp = claimtimestamp;
    }

    public String getClaimuser() {
        return claimuser;
    }

    public PIN claimuser(String claimuser) {
        this.claimuser = claimuser;
        return this;
    }

    public void setClaimuser(String claimuser) {
        this.claimuser = claimuser;
    }

    public String getClaimip() {
        return claimip;
    }

    public PIN claimip(String claimip) {
        this.claimip = claimip;
        return this;
    }

    public void setClaimip(String claimip) {
        this.claimip = claimip;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PIN pIN = (PIN) o;
        if (pIN.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pIN.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PIN{" +
            "id=" + getId() +
            ", account='" + getAccount() + "'" +
            ", pin='" + getPin() + "'" +
            ", createip='" + getCreateip() + "'" +
            ", createuser='" + getCreateuser() + "'" +
            ", createtimestamp='" + getCreatetimestamp() + "'" +
            ", expiretimestamp='" + getExpiretimestamp() + "'" +
            ", claimtimestamp='" + getClaimtimestamp() + "'" +
            ", claimuser='" + getClaimuser() + "'" +
            ", claimip='" + getClaimip() + "'" +
            "}";
    }
}
