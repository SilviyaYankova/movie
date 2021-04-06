package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.repository.CountryRepository;
import bg.softuni.movie.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public void seedCountries() {
        if (countryRepository.count() == 0) {
            CountryEntity afghanistan = new CountryEntity().setName(CountryEnum.AFGHANISTAN);
            CountryEntity armenia = new CountryEntity().setName(CountryEnum.ARMENIA);
            CountryEntity azerbaijan = new CountryEntity().setName(CountryEnum.AZERBAIJAN);
            CountryEntity bahrain = new CountryEntity().setName(CountryEnum.BAHRAIN);
            CountryEntity bangladesh = new CountryEntity().setName(CountryEnum.BANGLADESH);
            CountryEntity bhutan = new CountryEntity().setName(CountryEnum.BHUTAN);
            CountryEntity brunei = new CountryEntity().setName(CountryEnum.BRUNEI);
            CountryEntity cambodia = new CountryEntity().setName(CountryEnum.CAMBODIA);
            CountryEntity china = new CountryEntity().setName(CountryEnum.CHINA);
            CountryEntity cyprus = new CountryEntity().setName(CountryEnum.CYPRUS);
            CountryEntity georgia = new CountryEntity().setName(CountryEnum.GEORGIA);
            CountryEntity india = new CountryEntity().setName(CountryEnum.INDIA);
            CountryEntity indonesia = new CountryEntity().setName(CountryEnum.INDONESIA);
            CountryEntity iraq = new CountryEntity().setName(CountryEnum.IRAQ);
            CountryEntity iran = new CountryEntity().setName(CountryEnum.IRAN);
            CountryEntity israel = new CountryEntity().setName(CountryEnum.ISRAEL);
            CountryEntity japan = new CountryEntity().setName(CountryEnum.JAPAN);
            CountryEntity jordan = new CountryEntity().setName(CountryEnum.JORDAN);
            CountryEntity kazakhstan = new CountryEntity().setName(CountryEnum.KAZAKHSTAN);
            CountryEntity kuwait = new CountryEntity().setName(CountryEnum.KUWAIT);
            CountryEntity kyrgyzstan = new CountryEntity().setName(CountryEnum.KYRGYZSTAN);
            CountryEntity laos = new CountryEntity().setName(CountryEnum.LAOS);
            CountryEntity lebanon = new CountryEntity().setName(CountryEnum.LEBANON);
            CountryEntity malaysia = new CountryEntity().setName(CountryEnum.MALAYSIA);
            CountryEntity maldives = new CountryEntity().setName(CountryEnum.MALDIVES);
            CountryEntity mongolia = new CountryEntity().setName(CountryEnum.MONGOLIA);
            CountryEntity myanmar = new CountryEntity().setName(CountryEnum.MYANMAR);
            CountryEntity nepal = new CountryEntity().setName(CountryEnum.NEPAL);
            CountryEntity oman = new CountryEntity().setName(CountryEnum.OMAN);
            CountryEntity pakistan = new CountryEntity().setName(CountryEnum.PAKISTAN);
            CountryEntity palestine = new CountryEntity().setName(CountryEnum.PALESTINE);
            CountryEntity philippines = new CountryEntity().setName(CountryEnum.PHILIPPINES);
            CountryEntity qatar = new CountryEntity().setName(CountryEnum.QATAR);
            CountryEntity russia = new CountryEntity().setName(CountryEnum.RUSSIA);
            CountryEntity saudiArabia = new CountryEntity().setName(CountryEnum.SAUDI_ARABIA);
            CountryEntity singapore = new CountryEntity().setName(CountryEnum.SINGAPORE);
            CountryEntity korea = new CountryEntity().setName(CountryEnum.KOREA);
            CountryEntity sriLanka = new CountryEntity().setName(CountryEnum.SRI_LANKA);
            CountryEntity syria = new CountryEntity().setName(CountryEnum.SYRIA);
            CountryEntity taiwan = new CountryEntity().setName(CountryEnum.TAIWAN);
            CountryEntity tajikistan = new CountryEntity().setName(CountryEnum.TAJIKISTAN);
            CountryEntity thailand = new CountryEntity().setName(CountryEnum.THAILAND);
            CountryEntity turkey = new CountryEntity().setName(CountryEnum.TURKEY);
            CountryEntity turkmenistan = new CountryEntity().setName(CountryEnum.TURKMENISTAN);
            CountryEntity uzbekistan = new CountryEntity().setName(CountryEnum.UZBEKISTAN);
            CountryEntity vietnam = new CountryEntity().setName(CountryEnum.VIETNAM);
            CountryEntity yemen = new CountryEntity().setName(CountryEnum.YEMEN);

            countryRepository.saveAll(List.of(afghanistan, armenia, azerbaijan, bahrain, bangladesh,
                    bhutan, brunei, cambodia, china, cyprus, georgia, india, indonesia,
                    iran, iraq, israel, japan, jordan,
                    kazakhstan, korea,  kuwait, kyrgyzstan, laos, lebanon, malaysia, maldives, mongolia,
                    myanmar, nepal, oman, pakistan, palestine, philippines, qatar,
                    russia, saudiArabia, singapore, sriLanka, syria, taiwan,
                    tajikistan, thailand, turkey, turkmenistan, uzbekistan, vietnam, yemen));
        }
    }

    @Override
    public CountryEntity findCountry(CountryEnum name) {

        return countryRepository.findByName(name);
    }
}
