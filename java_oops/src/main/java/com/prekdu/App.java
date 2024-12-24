package com.prekdu;

import java.util.*;

/*
 * Design a Library Management System that handles different types of library resources
(books, digital content, periodicals). The system should demonstrate(Use enums, classes, functions etc):

1. Create an abstract class LibraryResource that contains:
   - Protected fields for resourceId, title, and availabilityStatus
   - Abstract method calculateLateFee(int daysLate)
   - Abstract method getMaxLoanPeriod()

2. Implement different types of resources:
   - Book: Has additional fields for author and ISBN
   - DigitalContent: Has fields for fileSize and format (PDF, EPUB)
   - Periodical: Has fields for issueNumber and frequency (WEEKLY, MONTHLY)

3. Create an interface Renewable with method:
   - boolean renewLoan(LibraryMember member)

4. Create an interface Reservable with methods:
   - void reserve(LibraryMember member)
   - void cancelReservation(LibraryMember member)

5. Implement a LibraryMember class that:
   - Maintains a list of borrowed resources
   - Has a membership type (STANDARD, PREMIUM)
   - Has methods to borrow and return resources

6. Implement proper exception handling for:
   - ResourceNotAvailableException
   - MaximumLoanExceededException
 - InvalidMembershipException

Requirements:
- Books and Periodicals should be both Renewable and Reservable
- DigitalContent should only be Renewable
- Different resource types should have different late fees and loan periods
- Premium members should have higher loan limits and lower late fees and loan periods
- Implement proper validation and business logic

 */

// Enum for membership types
enum MembershipType {
  STANDARD,
  PREMIUM
}

// Enum for resource status
enum ResourceStatus {
  AVAILABLE,
  BORROWED,
  RESERVED
}

// Enum for content format
enum ContentFormat {
  PDF,
  EPUB
}

// Enum for periodical frequency
enum Frequency {
  WEEKLY,
  MONTHLY
}

// Custom exceptions
class ResourceNotAvailableException extends RuntimeException {
  public ResourceNotAvailableException(String message) {
    super(message);
  }
}

class MaximumLoanExceededException extends RuntimeException {
  public MaximumLoanExceededException(String message) {
    super(message);
  }
}

class InvalidMembershipException extends RuntimeException {
  public InvalidMembershipException(String message) {
    super(message);
  }
}

// Abstract class for library resources
abstract class LibraryResource {
  protected String resourceId;
  protected String title;
  protected ResourceStatus status;

  public LibraryResource(String resourceId, String title) {
    this.resourceId = resourceId;
    this.title = title;
    this.status = ResourceStatus.AVAILABLE;
  }

  public ResourceStatus getStatus() {
    return status;
  }

  public void setStatus(ResourceStatus status) {
    this.status = status;
  }

  public abstract double calculateLateFee(int daysLate);

  public abstract int getMaxLoanPeriod();
}

// Interfaces
interface Renewable {
  boolean renewLoan(LibraryMember member);
}

interface Reservable {
  void reserve(LibraryMember member) throws IllegalStateException;

  void cancelReservation(LibraryMember member);
}

// Book class
class Book extends LibraryResource implements Renewable, Reservable {
  private String author;
  private String isbn;
  private LibraryMember reservedBy;

  public Book(String resourceId, String title, String author, String isbn) {
    super(resourceId, title);
    this.author = author;
    this.isbn = isbn;
  }

  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.5;
  }

  @Override
  public int getMaxLoanPeriod() {
    return 7;
  }

  @Override
  public boolean renewLoan(LibraryMember member) {
    return reservedBy == null;
  }

  @Override
  public void reserve(LibraryMember member) {
    if (reservedBy != null
        || this.getStatus() == ResourceStatus.AVAILABLE
        || this.getStatus() == ResourceStatus.RESERVED) {
      throw new IllegalStateException("Resource cannot be reserved.");
    }
    reservedBy = member;
    this.setStatus(ResourceStatus.RESERVED);
  }

  @Override
  public void cancelReservation(LibraryMember member) {
    if (reservedBy == member) {
      reservedBy = null;
      this.setStatus(ResourceStatus.BORROWED);
    }
  }
}

// DigitalContent class
class DigitalContent extends LibraryResource implements Renewable {
  private double fileSize;
  private ContentFormat format;

  public DigitalContent(String resourceId, String title, double fileSize, ContentFormat format) {
    super(resourceId, title);
    this.fileSize = fileSize;
    this.format = format;
  }

  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.25;
  }

  @Override
  public int getMaxLoanPeriod() {
    return 5;
  }

  @Override
  public boolean renewLoan(LibraryMember member) {
    return true;
  }
}

// Periodical class
class Periodical extends LibraryResource implements Renewable, Reservable {
  private int issueNumber;
  private Frequency frequency;
  private LibraryMember reservedBy;

  public Periodical(String resourceId, String title, int issueNumber, Frequency frequency) {
    super(resourceId, title);
    this.issueNumber = issueNumber;
    this.frequency = frequency;
  }

  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.6;
  }

  @Override
  public int getMaxLoanPeriod() {
    return 6;
  }

  @Override
  public boolean renewLoan(LibraryMember member) {
    return reservedBy == null;
  }

  @Override
  public void reserve(LibraryMember member) {
    if (reservedBy != null || this.getStatus() == ResourceStatus.RESERVED) {
      throw new IllegalStateException("Resource is already reserved.");
    }
    reservedBy = member;
    this.setStatus(ResourceStatus.RESERVED);
  }

  @Override
  public void cancelReservation(LibraryMember member) {
    if (reservedBy == member) {
      reservedBy = null;
      this.setStatus(ResourceStatus.BORROWED);
    }
  }
}

// LibraryMember class
class LibraryMember {
  private String memberId;
  private MembershipType membershipType;
  private List<LibraryResource> borrowedResources;

  public LibraryMember(String memberId, MembershipType membershipType) {
    this.memberId = memberId;
    this.membershipType = membershipType;
    this.borrowedResources = new ArrayList<>();
  }

  public MembershipType getMembershipType() {
    return membershipType;
  }

  public void borrowResource(LibraryResource resource) {
    if (resource.getStatus() != ResourceStatus.AVAILABLE) {
      throw new ResourceNotAvailableException("Resource is not available.");
    }

    int loanLimit = membershipType == MembershipType.PREMIUM ? 10 : 5;
    if (borrowedResources.size() >= loanLimit) {
      throw new MaximumLoanExceededException("Loan limit exceeded.");
    }

    borrowedResources.add(resource);
    resource.setStatus(ResourceStatus.BORROWED);
  }

  public void returnResource(LibraryResource resource) {
    borrowedResources.remove(resource);
    resource.setStatus(ResourceStatus.AVAILABLE);
  }

  public List<LibraryResource> getBorrowedResources() {
    return borrowedResources;
  }
}
