    package com.wegagen.efoyta.mapper;

    import com.wegagen.efoyta.dto.request.LoanRequestRequestDto;
    import com.wegagen.efoyta.dto.response.LoanRequestResponseDto;
    import com.wegagen.efoyta.dto.response.SimpleCustomerResponseDto;
    import com.wegagen.efoyta.dto.response.SimpleLoanProductResponseDto;
    import com.wegagen.efoyta.dto.response.SimpleLoanRequestResponseDto;
    import com.wegagen.efoyta.entity.LoanRequest;

    import java.time.format.DateTimeFormatter;

    public class LoanRequestMapper {

        public static LoanRequest mapToLoanRequestEntity(LoanRequestRequestDto requestDto){
            return LoanRequest.builder()
                    .businessLine(requestDto.getBusinessLine())
                    .haveYouVisitedTheBusinessAddress(requestDto.getHaveYouVisitedTheBusinessAddress())
                    .isBusinessAddressComfortableForFollowUp(requestDto.getIsBusinessAddressComfortableForFollowUp())
                    .requestingBranchName(requestDto.getRequestingBranchName())
                    .requestingBranchSenderName(requestDto.getRequestingBranchSenderName())
                    .build();

            //todo remove the sender name and branch code after implementing the security
        }

        public static LoanRequestResponseDto mapToLoanRequestResponseDto(LoanRequest loanRequest){
            return new LoanRequestResponseDto(
                    loanRequest.getLoanRequestId().toString(),
                    loanRequest.getRemarks().stream().map(RemarkMapper::mapToRemarkResponseDto).toList(),
                    loanRequest.getBusinessLine(),
                    loanRequest.getHaveYouVisitedTheBusinessAddress(),
                    loanRequest.getIsBusinessAddressComfortableForFollowUp(),
                    loanRequest.getRequestStatus().toString(),
                    loanRequest.getRequestingBranchName(),
                    loanRequest.getRequestingBranchSenderName(),
                    LoanProductMapper.mapToSimpleLoanProductResponse(loanRequest.getLoanProduct()),
                    CustomerMapper.mapToSimpleCustomerResponseDto(loanRequest.getCustomer()),
                    loanRequest.getCreatedAt().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")),
                    loanRequest.getUpdatedAt().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"))
                    );
        }

        public static SimpleLoanRequestResponseDto mapToSimpleLoanRequestResponseDto(LoanRequest loanRequest){
            return new SimpleLoanRequestResponseDto(
                    loanRequest.getLoanRequestId().toString(),
                    loanRequest.getRemarks().stream().map(RemarkMapper::mapToRemarkResponseDto).toList(),
                    loanRequest.getBusinessLine(),
                    loanRequest.getRequestStatus().toString(),
                    loanRequest.getRequestingBranchName(),
                    loanRequest.getRequestingBranchSenderName(),
                    loanRequest.getCreatedAt().toString(),
                    loanRequest.getUpdatedAt().toString()
                    );
        }
    }
