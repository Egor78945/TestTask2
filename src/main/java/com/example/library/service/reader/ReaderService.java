package com.example.library.service.reader;

import com.example.library.model.dto.ReaderDTO;
import com.example.library.model.dto.ReaderDeptDTO;
import com.example.library.model.entity.Reader;
import com.example.library.repository.ReaderRepository;
import com.example.library.service.reader.mapper.ReaderMapper;
import com.example.library.service.reader.validation.ReaderValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public void registration(ReaderDTO readerDTO){
        ReaderValidationService.isValid(readerDTO);
        if(!existsReaderByPhoneNumber(readerDTO.phoneNumber())){
            saveReader(ReaderMapper.mapTo(readerDTO));
            return;
        }
        throw new RuntimeException("Reader with same phone number already exists.");
    }

    public void saveReader(Reader reader) {
        readerRepository.save(reader);
    }

    public boolean existsReaderByPhoneNumber(String phoneNumber) {
        return readerRepository.existsReaderByPhoneNumber(phoneNumber);
    }

    public List<Reader> getMostReadingReaders() {
        return readerRepository.findMostReadingReaders().stream().map(arr -> new Reader((String) arr[0], (String) arr[1], (String) arr[2], (String) arr[3], (Date) arr[4])).collect(Collectors.toList());
    }

    public List<ReaderDeptDTO> getReadersDept(){
        return readerRepository.findReadersDept().stream().filter(arr -> (Long)arr[1] > 0).map(arr -> new ReaderDeptDTO((String) arr[0], (Long) arr[1])).collect(Collectors.toList());
    }
}
