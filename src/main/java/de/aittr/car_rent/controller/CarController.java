package de.aittr.car_rent.controller;

import de.aittr.car_rent.domain.dto.CarResponseDto;
import de.aittr.car_rent.service.interfaces.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Tag(name = "Car controller", description = "Controller for various operations with cars")
public class CarController {

    private final CarService carService;

    // POST -> localhost:8080/cars
    @PostMapping
    public CarResponseDto saveCar(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Instance of a Car")
            CarResponseDto carDto) {
        return carService.saveCar(carDto);
    }

    // GET -> localhost:8080/cars/all
    @GetMapping("/all")
    @Operation(
            summary = "Get all cars",
            description = "Getting all cars that exist in the database"
    )
    public List<CarResponseDto> getAllCars() {
        return carService.getAllCars();
    }

    // GET -> localhost:8080/cars/id/5
    @GetMapping("/id/{id}")
    public CarResponseDto getCarById(
            @PathVariable
            @Parameter(description = "Car unique identifier")
            Long id) {
        return carService.getCarById(id);
    }

    // GET -> localhost:8080/cars/brand/toyota
    @GetMapping("/brand/{brand}")
    public List<CarResponseDto> getCarsByBrand(
            @PathVariable
            @Parameter(description = "Car brand title")
            String brand) {
        return carService.getCarsByBrand(brand);
    }

    // GET -> localhost:8080/cars/model/corolla
    @GetMapping("/model/{model}")
    public List<CarResponseDto> getCarsByModel(
            @PathVariable
            @Parameter(description = "Car model title")
            String model) {
        return carService.getCarsByModel(model);
    }

    // GET -> localhost:8080/cars/year/2006
    @GetMapping("/year/{year}")
    public List<CarResponseDto> getCarsByYear(
            @PathVariable
            @Parameter(description = "Car build year")
            int year) {
        return carService.getCarsByYear(year);
    }

    // GET -> localhost:8080/cars/type/sedan
    @GetMapping("/type/{type}")
    public List<CarResponseDto> getCarsByType(
            @PathVariable
            @Parameter(description = "Car type title")
            String type) {
        return carService.getCarsByType(type);
    }

    // GET -> localhost:8080/cars/fuel-type/petrol
    @GetMapping("/fuel-type/{fuelType}")
    public List<CarResponseDto> getCarsByFuelType(
            @PathVariable
            @Parameter(description = "Car fuel type title")
            String fuelType) {
        return carService.getCarsByFuelType(fuelType);
    }

    // GET -> localhost:8080/cars/transmission-type/manual
    @GetMapping("/transmission-type/{transmissionType}")
    public List<CarResponseDto> getCarsByTransmissionType(
            @PathVariable
            @Parameter(description = "Car transmission type title")
            String transmissionType) {
        return carService.getCarsByTransmissionType(transmissionType);
    }

    // GET -> localhost:8080/cars/available
    // GET -> localhost:8080/cars/car-status/under_repair
    @GetMapping("/car-status/{carStatus}")
    public List<CarResponseDto> getCarsByCarStatus(
            @PathVariable
            @Parameter(description = "Car status title")
            String carStatus) {
        return carService.getCarsByCarStatus(carStatus);
    }

    // GET -> localhost:8080/cars/rental-price/150-200
    @GetMapping("/rental-price/{minDayRentalPrice}-{maxDayRentalPrice}")
    public List<CarResponseDto> getCarsByDayRentalPrice(
            @PathVariable
            @Parameter(description = "Minimal day rental car price")
            BigDecimal minDayRentalPrice,

            @PathVariable
            @Parameter(description = "Maximal day rental car price")
            BigDecimal maxDayRentalPrice) {
        return carService.getCarsByDayRentalPrice(minDayRentalPrice, maxDayRentalPrice);
    }

    // PUT -> localhost:8080/cars  (идентификатор отправляется в теле запроса)
    @PutMapping
    @Operation(
            summary = "Update car",
            description = "Update car day rental price and car status in the database based on the passed CarResponseDto object"
    )
    public void updateCar(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Instance of a Car")
            CarResponseDto carDto) {
        carService.updateCar(carDto);
    }

    // DELETE -> localhost:8080/cars/3
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete car",
            description = "Change car property active in the database on false")
    public void deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

//    GET-> localhost:5195/api/cars/filter?startDateTime=2025-03-30T16%3A43&endDateTime=2025-04-03T16%3A43&minPrice=20&maxPrice=100&fuel=petrol&type=sedan&brand=toyota
    @GetMapping("/filter")
    @Operation(
            summary = "Filter available cars",
            description = "Filter available cars by various criteria including dates, brand, fuel type, car type, transmission type and price range"
    )
    public List<CarResponseDto> filterAvailableCars(
            @RequestParam(required = false)
            @Parameter(description = "Start date and time of rental period")
            LocalDateTime startDateTime,

            @RequestParam(required = false)
            @Parameter(description = "End date and time of rental period")
            LocalDateTime endDateTime,

            @RequestParam(required = false)
            @Parameter(description = "Car brand")
            List <String> brand,

            @RequestParam(required = false)
            @Parameter(description = "Car fuel type")
            List <String> fuel,

            @RequestParam(required = false)
            @Parameter(description = "Car transmission type")
            List <String> transmissionType,

            @RequestParam(required = false)
            @Parameter(description = "Car body type")
            List <String> type,

            @RequestParam(required = false)
            @Parameter(description = "Minimum rental price per day")
            BigDecimal minPrice,

            @RequestParam(required = false)
            @Parameter(description = "Maximum rental price per day")
            BigDecimal maxPrice) {
        return carService.filterAvailableCars(startDateTime, endDateTime, brand, fuel, transmissionType, type, minPrice, maxPrice);
    }

//   GET -> localhost:8080/api/cars/brands
    @GetMapping("/brands")
    @Operation(
            summary = "Get all available car brands",
            description = "Get a list of all unique car brands that a currently active"
    )
    public List<String> getAllAvailableBrands() {
        return carService.getAllAvailableBrands();
    }

    @GetMapping("/types")
    @Operation(
            summary = "Get all car types",
            description = "Retrieve a list of all car body types"
    )
    public List<String> getAllCarTypes() {
        return carService.getAllCarTypes();
    }
}
