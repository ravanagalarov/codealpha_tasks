package com.ravan.hotel_reservation.serviceImpl;

import com.ravan.hotel_reservation.DTOs.PaymentRequestDTO;
import com.ravan.hotel_reservation.DTOs.PaymentResponseDTO;
import com.ravan.hotel_reservation.ExceptionHandler.ResourceNotFoundException;
import com.ravan.hotel_reservation.entity.Payment;
import com.ravan.hotel_reservation.enums.PaymentStatus;
import com.ravan.hotel_reservation.repository.PaymentRepository;
import com.ravan.hotel_reservation.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final ModelMapper mapper;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(ModelMapper mapper, PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {
        Payment created = mapper.map(dto, Payment.class);
        paymentRepository.save(created);

        return mapper.map(created, PaymentResponseDTO.class);
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);

        return mapper.map(payment, PaymentResponseDTO.class);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        List<Payment> paymentList = paymentRepository.findAll();
        if (paymentList.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        List<PaymentResponseDTO> dtoList = paymentList.stream().
                map(payment -> mapper.map(payment, PaymentResponseDTO.class)).toList();

        return dtoList;
    }


    @Override
    @Transactional
    public PaymentResponseDTO updatePaymentMethod(Long id, String newMethod) {
        Payment payment = paymentRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);

        payment.setPaymentMethod(newMethod);
        Payment updatedMethod = paymentRepository.save(payment);

        return mapper.map(updatedMethod, PaymentResponseDTO.class);
    }

    @Override
    @Transactional
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);

        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentResponseDTO getPaymentByReservationId(Long reservationId) {
        Payment payment = paymentRepository.findPaymentByReservationId(reservationId)
                .orElseThrow(ResourceNotFoundException::new);

        return mapper.map(payment, PaymentResponseDTO.class);
    }

    @Override
    public List<PaymentResponseDTO> getPaymentByStatus(PaymentStatus status) {
        List<Payment> paymentList = paymentRepository.findByStatus(status);

        if (paymentList.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        List<PaymentResponseDTO> dtos = paymentList.stream().
                map(payment -> mapper.map(payment, PaymentResponseDTO.class)).toList();

        return dtos;
    }
}
