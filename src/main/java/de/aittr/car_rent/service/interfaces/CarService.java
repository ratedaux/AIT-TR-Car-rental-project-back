package de.aittr.car_rent.service.interfaces;

import de.aittr.car_rent.domain.dto.CarResponseDto;
import de.aittr.car_rent.domain.entity.Car;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface CarService {

    /**
     * Метод сохраняет авто в базе данных (сохраненяется авто со статуслм активный)
     * @param carDto со всеми свойствами
     * @return CarResponseDto сохранённой машины
     */
    CarResponseDto saveCar(CarResponseDto carDto);

    /**
     * Метод получения всех авто (активные), имеющихся в БД
     * @return список активных авто
     */
    List<CarResponseDto> getAllCars();

    /**
     * Метод нахождения авто по его id
     * @param id авто
     * @return CarResponseDto найденной машины
     */
    CarResponseDto getCarById(Long id);

    /**
     * Метод нахождения машины по её id. Если машина не найдена, будет выброшено исключение
     * @param id машины
     * @return entity найденной машины
     *
     */
    Car getOrThrow(Long id);

    /**
     * Метод нахождения авто по их бренду. Если машины не найдена, будет возвращён пустой список
     * @param brand авто
     * @return список CarResponseDto машин переданного в метод бренда
     */
    List<CarResponseDto> getCarsByBrand(String brand);

    /**
     * Метод нахождения авто по их модели. Если машины не найдена, будет возвращён пустой список
     * @param model авто
     * @return список CarResponseDto машин переданной в метод модели
     */
    List<CarResponseDto> getCarsByModel(String model);

    /**
     * Метод нахождения авто по году выпуска. Если машины не найдена, будет возвращён пустой список
     * @param year авто
     * @return список CarResponseDto машин переданного в метод года выпуска
     */
    List<CarResponseDto> getCarsByYear(int year);

    /**
     * Метод нахождения авто по типу кузова. Если машины не найдена, будет возвращён пустой список
     * @param type кузова авто
     * @return список CarResponseDto машин переданного в метод типа кузова
     */
    List<CarResponseDto> getCarsByType(String type);

    /**
     * Метод нахождения авто по типу топлива. Если машины не найдена, будет возвращён пустой список
     * @param fuelType авто
     * @return список CarResponseDto машин переданного в метод типа топлива
     */
    List<CarResponseDto> getCarsByFuelType(String fuelType);

    /**
     * Метод нахождения авто по типу коробки передач. Если машины не найдена, будет возвращён пустой список
     * @param transmissionType авто
     * @return список CarResponseDto машин переданного в метод типа коробки передач
     */
    List<CarResponseDto> getCarsByTransmissionType(String transmissionType);

    /**
     * Метод нахождения авто по статусу авто (доступно, арендовано, в ремонте, снято с аренды). Если машины не найдена, будет возвращён пустой список
     * @param carStatus авто
     * @return список CarResponseDto машин c переданным в метод статусом
     */
    List<CarResponseDto> getCarsByCarStatus(String carStatus);

    /**
     * Метод нахождения авто по стоимости их аренды в день. Если машины не найдена, будет возвращён пустой список
     * @param minDayRentalPrice
     * @param maxDayRentalPrice
     * @return список CarResponseDto машин c арендой в день, попадающей в переданные в метод пределы включительно
     */
    List<CarResponseDto> getCarsByDayRentalPrice(BigDecimal minDayRentalPrice, BigDecimal maxDayRentalPrice);

    /**
     * Метод изменения статуса и стоимости аренды в день авто. Если машина не найдена метод выбросит исключение
     * @param carDto
     *
     */
    void updateCar(CarResponseDto carDto);

    /**
     * Метод удаления авто из спискка доступных пользователю. Если машина не найдена метод выбросит исключение
     * @param id
     */
    void deleteCarById(Long id);

    //восстановить авто по его id

    //добавить ссылку на изображение авто по id авто

    /**
     * Метод добавления фото к машине.
     * @param id
     * @param imageUrl
     */
    void attachImageToCar(Long id, String imageUrl);

    List<CarResponseDto> getAllAvailableCarsByDates(LocalDateTime startDate, LocalDateTime endDate);

    List<CarResponseDto> filterAvailableCars(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            List<String> brand,
            List<String> fuel,
            List<String> transmissionType,
            List<String> type,
            BigDecimal minPrice,
            BigDecimal maxPrice);

    List<String> getAllAvailableBrands();

    List<String> getAllCarTypes();
}
