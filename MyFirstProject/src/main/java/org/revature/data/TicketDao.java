package org.revature.data;

import org.revature.entity.Ticket;
import java.util.List;


public interface TicketDao {


    Ticket  insertTicket(Ticket ticket);
    List<Ticket> getById(int employerId);

    Ticket getByTicketId(int ticketId);
    List<Ticket> getByTicketStatus(String ticketStatus, int employeeid);
    List<Ticket> viewAll();
    Ticket updateTicket(Ticket ticket);
}
