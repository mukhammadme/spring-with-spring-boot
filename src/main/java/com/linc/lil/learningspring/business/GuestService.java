package com.linc.lil.learningspring.business;

import com.linc.lil.learningspring.data.Guest;
import com.linc.lil.learningspring.data.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuests() {
        Iterable<Guest> guests = this.guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();
        guests.forEach(guest -> {guestList.add(guest);});
        guestList.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest o1, Guest o2) {
                if (o1.getLastName().equals(o2.getLastName())) {
                    return o1.getFirstName().compareTo(o2.getFirstName());
                }
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });

        return guestList;
    }

    public Guest addGuest(Guest guest) {
        if (guest == null) {
            throw new RuntimeException("Guest cannot be null");
        }
        return this.guestRepository.save(guest);
    }

    public Optional<Guest> getGuestById(long guestId) {
        return this.guestRepository.findById(guestId);
    }
}
