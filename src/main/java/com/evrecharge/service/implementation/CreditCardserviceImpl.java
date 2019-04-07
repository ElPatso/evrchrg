package com.evrecharge.service.implementation;

import com.evrecharge.dto.CreditCardDTO;
import com.evrecharge.entity.CreditCard;
import com.evrecharge.repository.CreditCardRepository;
import com.evrecharge.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditCardserviceImpl implements CreditCardService {

    private CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardserviceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    @Transactional
    public CreditCardDTO getByCurrentUser() {
        CreditCard creditCard = creditCardRepository.getOne(1L);
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setExpMonth(creditCard.getExpireMonth());
        creditCardDTO.setExpYear(creditCard.getExpireYear());
        creditCardDTO.setCvc(creditCard.getCvc());
        creditCardDTO.setCardNumber(creditCard.getCardNumber());
        return creditCardDTO;
    }
}
