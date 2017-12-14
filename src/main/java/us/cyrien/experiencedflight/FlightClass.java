package us.cyrien.experiencedflight;

public enum FlightClass {
    ECONOMY_CLASS {
        @Override
        public float getCost() {
            Float f = (float) ExperiencedFlight.getInstance().getExpFlightConfig().getDouble("Economy-Class-Cost");
            f = checkCost(f, "Economy-Class-Cost");
            return f;
        }

        public float getSpeed() {
            Float f = (float) ExperiencedFlight.getInstance().getExpFlightConfig().getDouble("Economy-Class-Speed");
            if (f > 1.0f)
                f = 1.0f;
            else if (f <= 0.0f)
                f = 0.1f;
            return f;
        }

        @Override
        public String toString() {
            return "Economy class";
        }
    }, BUSINESS_CLASS {
        @Override
        public float getCost() {
            Float f = (float) ExperiencedFlight.getInstance().getExpFlightConfig().getDouble("Business-Class-Cost");
            f = checkCost(f, "Business-Class-Cost");
            return f;
        }

        public float getSpeed() {
            Float f = (float) ExperiencedFlight.getInstance().getExpFlightConfig().getDouble("Business-Class-Speed");
            if (f > 1.0f)
                f = 1.0f;
            else if (f <= 0.0f)
                f = 0.1f;
            return f;
        }


        @Override
        public String toString() {
            return "Business class";
        }
    }, FIRST_CLASS {
        @Override
        public float getCost() {
            Float f = (float) ExperiencedFlight.getInstance().getExpFlightConfig().getDouble("First-Class-Cost");
            f = checkCost(f, "First-Class-Cost");
            return f;
        }

        public float getSpeed() {
            Float f = (float) ExperiencedFlight.getInstance().getExpFlightConfig().getDouble("First-Class-Speed");
            if (f > 1.0f)
                f = 1.0f;
            else if (f <= 0.0f)
                f = 0.1f;
            return f;
        }


        @Override
        public String toString() {
            return "First class";
        }
    }, TERRORIST {
        @Override
        public float getCost() {
            return 3f;
        }

        public float getSpeed() {
            return 0.05f;
        }

        @Override
        public String toString() {
            return "Terrorist";
        }
    };

    public abstract float getCost();

    public abstract float getSpeed();

    public Float checkCost(Float f, String path) {
        if(f < 1) {
            String[] commentArr = new String[]{"Cost of flight for players", "with expflight.economy permission", "Can only be greater than 1"};
            ExperiencedFlight.getInstance().getExpFlightConfig().set(path, 1.1, commentArr);
            ExperiencedFlight.getInstance().getExpFlightConfig().saveConfig();
            ExperiencedFlight.getInstance().getExperiencedFlightConfigManager().reloadAllConfig();
            ExperiencedFlight.getInstance().getExperiencedFlightConfigManager().setupConfigurations();
            return 1.1f;
        }
        return f;
    }

}
