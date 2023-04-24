package com.linc.lil.learningspring.webservice;

import com.linc.lil.learningspring.business.GuestService;
import com.linc.lil.learningspring.business.ReservationService;
import com.linc.lil.learningspring.business.RoomReservation;
import com.linc.lil.learningspring.business.RoomService;
import com.linc.lil.learningspring.data.Guest;
import com.linc.lil.learningspring.data.Room;
import com.linc.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;
    private final GuestService guestService;
    private final RoomService roomService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService, RoomService roomService, GuestService guestService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
        this.guestService = guestService;
        this.roomService = roomService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        System.out.println("Date:: " + dateString);
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping("/guests")
    public List<Guest> getGuests() {
        return this.guestService.getAllGuests();
    }

    @GetMapping("/guests/{id}")
    public Optional<Guest> getGuestById(@PathVariable long id) {
        return this.guestService.getGuestById(id);
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addGuest(@RequestBody Guest guest) {
        return this.guestService.addGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return this.roomService.getRooms();
    }
}
