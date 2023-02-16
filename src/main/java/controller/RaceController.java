package controller;

import domain.Car;
import domain.Participants;
import domain.Race;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import util.NumberGenerator;
import view.input.InputView;
import view.output.OutputView;

public class RaceController {

    private Race race;
    private final Participants participants;
    private static final int DRIVING_DISTANCE = 1;

    public RaceController() {
        participants = new Participants();
    }

    public void getParticipants(InputView inputView) {
        inputView.showEnterCarNameMessage();
        getCarNamesUntilValid(inputView);
    }

    private void getCarNamesUntilValid(InputView inputView) {
        try {
            String input = inputView.getInputUntilExist();
            joinAllParticipants(input);
        } catch (IllegalArgumentException exception) {
            inputView.printError(exception);
            getCarNamesUntilValid(inputView);
        }
    }

    public void getCount(InputView inputView) {
        inputView.showEnterCountMessage();
        getCountUntilValid(inputView);
    }


    private void getCountUntilValid(InputView inputView) {
        try {
            race = new Race(inputView.getInputUntilExist());
        } catch (IllegalArgumentException exception) {
            inputView.printError(exception);
            getCountUntilValid(inputView);
        }
    }

    public void joinAllParticipants(String carNames) {
        final String delimiter = ",";
        try {
            splitWordsBy(carNames, delimiter).forEach((carName) -> participants.join(carName.strip()));
        } catch (Exception exception) {
            participants.reset();
            throw exception;
        }
    }

    private List<String> splitWordsBy(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter, -1)).map(String::strip)
            .collect(Collectors.toList());
    }
    
    public List<Car> getWinners() {
        List<Car> candidates = participants.showAllParticipants();
        int maxDistance = candidates.stream()
            .map(Car::getDrivenDistance)
            .max(Comparator.naturalOrder())
            .orElse(0);
        return candidates.stream()
            .filter(car -> car.isWinner(maxDistance))
            .collect(Collectors.toList());
    }

    public void driveOrNot(Car car, NumberGenerator numberGenerator) {
        int number = numberGenerator.generate();
        if (isEnoughToMove(number)) {
            car.drive(DRIVING_DISTANCE);
        }
    }

    private boolean isEnoughToMove(final int score) {
        final int minScore = 4;
        return score >= minScore;
    }

    public void printRoundResult(OutputView outputView) {
        outputView.printRoundResult(participants.showAllParticipants());
    }

    public void roundFinished() {
        race.addCount();
    }

    public List<Car> showStatus() {
        return participants.showAllParticipants();
    }

    public boolean isFinished() {
        return race.isFinished();
    }
}