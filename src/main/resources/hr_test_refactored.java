package HR_DB_Query;

import java.util.Scanner;

public class HR_Test {

    public static void main(String[] args) {
        // claude revised: Scanner를 하나만 사용하도록 통합
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        // claude revised: String 비교를 equals()로 변경하고 while 조건 수정
        while (!userInput.equals("Q")) {
            displayMenu();
            userInput = scanner.nextLine().trim(); // claude revised: trim() 추가로 공백 제거

            switch (userInput) {
                case "1":
                    handleSearchByName(scanner);
                    break;
                case "2":
                    handleSearchByHireYear(scanner);
                    break;
                case "3":
                    handleSearchByDepartment(scanner);
                    break;
                case "4":
                    handleSearchByJob(scanner);
                    break;
                case "5":
                    handleSearchByCity(scanner);
                    break;
                case "6":
                    handleStatistics();
                    break;
                case "7":
                    handleSearchByManager(scanner);
                    break;
                case "8":
                    handleSearchByCountry(scanner);
                    break;
                case "Q":
                    System.out.println("종료합니다.");
                    break;
                default:
                    // claude revised: 잘못된 입력에 대한 처리 추가
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
        
        // claude revised: Scanner 리소스 정리
        scanner.close();
    }

    // claude revised: 메뉴 출력을 별도 메소드로 분리
    private static void displayMenu() {
        System.out.println("\n======================================");
        System.out.println("한국국토정보공사 인사정보시스템 (종료: Q)");
        System.out.println("======================================");
        System.out.println("1. 직원이름으로 직원정보를 검색");
        System.out.println("2. 입사년도로 직원정보를 검색");
        System.out.println("3. 부서번호로 직원정보를 검색");
        System.out.println("4. 직무로 직원정보를 검색");
        System.out.println("5. 도시이름으로 직원정보를 검색");
        System.out.println("6. 직원들의 통계자료를 출력");
        System.out.println("7. 부서장 성으로 그 부서의 직원정보를 검색");
        System.out.println("8. 나라 이름으로 그 나라에 근무하는 직원정보를 검색");
        System.out.print("선택하세요: ");
    }

    // claude revised: 각 케이스를 별도 메소드로 분리하여 가독성 향상
    private static void handleSearchByName(Scanner scanner) {
        System.out.println("\n=== 직원 이름 검색 ===");
        System.out.print("이름을 입력(성 혹은 이름): ");
        try {
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) { // claude revised: 빈 문자열 체크 추가
                EmployeeDAO.findByName(name);
            } else {
                System.out.println("이름을 입력해주세요.");
            }
        } catch (Exception e) {
            System.err.println("검색 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private static void handleSearchByHireYear(Scanner scanner) {
        System.out.println("\n=== 입사년도별 검색 ===");
        System.out.print("입사년도 입력: ");
        try {
            String yearString = scanner.nextLine().trim();
            if (!yearString.isEmpty()) {
                int year = Integer.parseInt(yearString);
                // claude revised: 입력값 유효성 검사 추가
                if (year > 1900 && year <= java.time.Year.now().getValue()) {
                    EmployeeDAO.findByHireYear(year);
                } else {
                    System.out.println("유효한 연도를 입력해주세요.");
                }
            } else {
                System.out.println("연도를 입력해주세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("올바른 숫자를 입력해주세요.");
        } catch (Exception e) {
            System.err.println("검색 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private static void handleSearchByDepartment(Scanner scanner) {
        System.out.println("\n=== 부서번호별 검색 ===");
        System.out.print("부서번호 입력: ");
        try {
            String deptIDString = scanner.nextLine().trim();
            if (!deptIDString.isEmpty()) {
                int deptID = Integer.parseInt(deptIDString);
                // claude revised: 부서번호 유효성 검사 추가
                if (deptID > 0) {
                    EmployeeDAO.findByDeptId(deptID);
                } else {
                    System.out.println("유효한 부서번호를 입력해주세요.");
                }
            } else {
                System.out.println("부서번호를 입력해주세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("올바른 숫자를 입력해주세요.");
        } catch (Exception e) {
            System.err.println("검색 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private static void handleSearchByJob(Scanner scanner) {
        System.out.println("\n=== 직무별 검색 ===");
        displayJobList(); // claude revised: 직무 목록을 별도 메소드로 분리
        System.out.print("직무를 입력: ");
        try {
            String job = scanner.nextLine().trim().toUpperCase(); // claude revised: 대소문자 통일
            if (!job.isEmpty()) {
                EmployeeDAO.getEmpListByJobId(job);
            } else {
                System.out.println("직무를 입력해주세요.");
            }
        } catch (Exception e) {
            System.err.println("검색 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // claude revised: 직무 목록 출력을 별도 메소드로 분리
    private static void displayJobList() {
        System.out.println("직무는 다음과 같습니다:");
        System.out.println("AC_ACCOUNT  AC_MGR     AD_ASST    AD_PRES");
        System.out.println("AD_VP       FI_ACCOUNT FI_MGR     HR_REP");
        System.out.println("IT_PROG     MK_MAN     MK_REP     PR_REP");
        System.out.println("PU_CLERK    PU_MAN     SA_MAN     SA_REP");
        System.out.println("SH_CLERK    ST_CLERK   ST_MAN");
    }

    private static void handleSearchByCity(Scanner scanner) {
        System.out.println("\n=== 도시별 검색 ===");
        displayCityList(); // claude revised: 도시 목록을 별도 메소드로 분리
        System.out.print("도시를 입력: ");
        try {
            String cityName = scanner.nextLine().trim();
            if (!cityName.isEmpty()) {
                EmployeeDAO.getEmpListByCityName(cityName);
            } else {
                System.out.println("도시명을 입력해주세요.");
            }
        } catch (Exception e) {
            System.err.println("검색 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // claude revised: 도시 목록 출력을 별도 메소드로 분리
    private static void displayCityList() {
        System.out.println("도시는 다음과 같습니다:");
        System.out.println("Hiroshima           London               Mexico City         Munich");
        System.out.println("Oxford              Roma                 Sao Paulo           Seattle");
        System.out.println("Singapore           South Brunswick      South San Francisco Southlake");
        System.out.println("Stretford           Sydney               Tokyo               Toronto");
        System.out.println("Utrecht             Venice               Whitehorse");
    }

    private static void handleStatistics() {
        System.out.println("\n=== 통계자료 출력 ===");
        try {
            EmployeeDAO.getHRstatistics();
        } catch (Exception e) {
            System.err.println("통계 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private static void handleSearchByManager(Scanner scanner) {
        System.out.println("\n=== 부서장별 검색 ===");
        displayManagerList(); // claude revised: 부서장 목록을 별도 메소드로 분리
        System.out.print("찾고싶은 부서의 부서장 이름을 입력: ");
        try {
            String managerName = scanner.nextLine().trim();
            if (!managerName.isEmpty()) {
                EmployeeDAO.getEmpListByManagerName(managerName);
            } else {
                System.out.println("부서장 이름을 입력해주세요.");
            }
        } catch (Exception e) {
            System.err.println("검색 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // claude revised: 부서장 목록 출력을 별도 메소드로 분리
    private static void displayManagerList() {
        System.out.println("부서장의 last_name은 다음과 같습니다:");
        System.out.println("Whalen     Li         Jacobs     Fripp");
        System.out.println("James      Brown      Singh      King");
        System.out.println("Gruenberg  Higgin");
    }

    private static void handleSearchByCountry(Scanner scanner) {
        System.out.println("\n=== 국가별 검색 ===");
        displayCountryList(); // claude revised: 국가 목록을 별도 메소드로 분리
        System.out.print("나라를 입력: ");
        try {
            String countryName = scanner.nextLine().trim();
            if (!countryName.isEmpty()) {
                EmployeeDAO.getEmpListByCountryName(countryName);
            } else {
                System.out.println("국가명을 입력해주세요.");
            }
        } catch (Exception e) {
            System.err.println("검색 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // claude revised: 국가 목록 출력을 별도 메소드로 분리하고 목록 완성
    private static void displayCountryList() {
        System.out.println("나라는 다음과 같습니다:");
        System.out.println("United States of America");
        System.out.println("Canada");
        System.out.println("United Kingdom of Great Britain and Northern Ireland");
        System.out.println("Germany");
        System.out.println("Japan");
        System.out.println("Italy");
        System.out.println("Singapore");
        System.out.println("Brazil");
    }
}