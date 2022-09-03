package org.revature.service;

import org.revature.data.DaoFactory;
import org.revature.data.TicketDao;
import org.revature.entity.Ticket;

import java.util.List;

public class TicketService {
    public Ticket insertTicket(Ticket ticket) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.insertTicket(ticket);
    }
    public List<Ticket> getById(int employeeId){
        TicketDao ticketDao= DaoFactory.getTicketDao();
        return ticketDao.getById(employeeId);

    }
    public Ticket getTicketById(int ticketId){
        TicketDao ticketDao= DaoFactory.getTicketDao();
        return ticketDao.getByTicketId(ticketId);

    }
    public List<Ticket> getTicketByStatus(String ticketStatus,int employeeid) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getByTicketStatus(ticketStatus,employeeid);
    }

        public List<Ticket> viewAll() {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.viewAll();
    }

    public Ticket updateTicket(Ticket ticket) {
        TicketDao ticketDao=DaoFactory.getTicketDao();
        return ticketDao.updateTicket(ticket );
    }
    }

