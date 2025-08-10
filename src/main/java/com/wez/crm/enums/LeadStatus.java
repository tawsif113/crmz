package com.wez.crm.enums;

public enum LeadStatus {
    NEW,          // Lead is newly created
    CONTACTED,    // Initial contact has been made
    QUALIFIED,    // Lead has been qualified as a potential customer
    PROPOSAL_SENT,// Proposal has been sent to the lead
    NEGOTIATION,  // Currently in negotiation phase
    WON,          // Lead has been converted into a customer
    LOST          // Lead was not converted into a customer
}
