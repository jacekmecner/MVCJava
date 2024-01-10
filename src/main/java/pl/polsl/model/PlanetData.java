package pl.polsl.model;
/**
     * PlanetData class representing data for each country.
     * Implements comparable to enable sorting based on activeCases.
     * @author Jacek Mecner
     * @version 1.0
     *
     */
    class PlanetData implements Comparable<PlanetData> {

        /**
         * Constructs a CountryData object for a specific planet with the
         * provided parameters.
         */
        PlanetData() {

        }
        private String _planetName;
        private double _mass;
        private double _dayLength;
        private double _rotationPeriod;
        private double _distanceFromSun;
        private double _averageTemperature;
        private String _hasGlobalMagneticField;
        
        void set_planetName(String planetName){
            _planetName = planetName;
        }
        
        String get_planetName(){
            return _planetName;
        }
        
        void set_mass(double mass){
            _mass = mass;
        }
        double get_mass() {
            return _mass;
        }
        
        void set_dayLength(double dayLength){
            _dayLength = dayLength;
        }
        double get_dayLength() {
            return _dayLength;
        }   
        
        void set_rotationPeriod(double rotationPeriod){
            _rotationPeriod = rotationPeriod;
        }
        double get_rotationPeriod() {
            return _rotationPeriod;
        }
        
        void set_distanceFromSun(double distanceFromSun){
            _distanceFromSun = distanceFromSun;
        }
        double get_distanceFromSun() {
            return _distanceFromSun;
        }
        
        void set_averageTemperature(double averageTemperature){
            _averageTemperature = averageTemperature;
        }
        double get_averageTemperature() {
            return _averageTemperature;
        }
        
        void set_hasGlobalMagneticField(String hasGlobalMagneticField){
            _hasGlobalMagneticField = hasGlobalMagneticField;
        }
        String get_hasGlobalMagneticField() {
            return _hasGlobalMagneticField;
        }
      
        /**
         * Compares this PlanetData object with another based on dayLength.
         *
         */
        @Override
        public int compareTo(PlanetData other) {
            return Double.compare(this._mass, other._mass);
        }
    }