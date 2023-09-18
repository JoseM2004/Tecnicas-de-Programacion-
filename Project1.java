public class ODSTrabajoInformal 
{
    record Municipality (String name, Integer population, Double informalityPercentage, Integer employedPeople){}
    
    public static void main(String[] args) 
    {
        List<Municipality> MunicipalitiesAntioquia = new ArrayList<>();
        MunicipalitiesAntioquia.add(new Municipality("Caracoli",
        4600,
        0.8,
        3800));
        
        MunicipalitiesAntioquia.add(new Municipality("La Ceja",
        63000,
        0.44,
        50000));
        
        MunicipalitiesAntioquia.add(new Municipality("Puerto Berrio",
        51000,
        0.82,
        48000));
        
        MunicipalitiesAntioquia.add(new Municipality("Carmen de Viboral",
        49600,
        0.63,
        43000));
        
        MunicipalitiesAntioquia.add(new Municipality("Maceo",
        5128,
        0.78,
        4000));
        
        MunicipalitiesAntioquia.add(new Municipality("Guarne",
        55000,
        0.59,
        47000));
        
        MunicipalitiesAntioquia.add(new Municipality("Guatape",
        9200,
        0.73,
        78000));
        
        MunicipalitiesAntioquia.add(new Municipality("San Roque",
        18000,
        0.92,
        15000));
        
        MunicipalitiesAntioquia.add(new Municipality("San Luis",
        13000,
        0.86,
        9000));
        
        MunicipalitiesAntioquia.add(new Municipality("Betania",
        11000,
        0.92,
        8000));
        
        MunicipalitiesAntioquia.add(new Municipality("Puerto Nare",
        14300,
        0.82,
        11500));
        
        MunicipalitiesAntioquia.add(new Municipality("San Carlos",
        11800,
        0.93,
        9200));
        
        MunicipalitiesAntioquia.add(new Municipality("La Union",  
        33000,
        0.73,
        22000));
        
        MunicipalitiesAntioquia.add(new Municipality("Marinilla",
        69000,
        0.70,
        62860));
        
        MunicipalitiesAntioquia.add(new Municipality("Amaga",
        32000,
        0.58,
        29000));
        
        MunicipalitiesAntioquia.add(new Municipality("Sonson",
        38200,
        0.86,
        27000));
        
        MunicipalitiesAntioquia.add(new Municipality("Puerto Triunfo",
        18000,
        0.83,
        14300));
        
        MunicipalitiesAntioquia.add(new Municipality("Rionegro",
        146000,
        0.56,
        10500));
        
        MunicipalitiesAntioquia.add(new Municipality("Nariño",
        10170,
        0.92,
        7800));
        
        MunicipalitiesAntioquia.add(new Municipality("Argelia",
        6000,
        0.81,
        4879));
        
        MunicipalitiesAntioquia.add(new Municipality("San Vicente",
        23300,
        0.82,
        17700));
        
        Double average, accumulated = 0D;
        
        for(int i=0; i<MunicipalitiesAntioquia.size(); i++)
        {
            accumulated += MunicipalitiesAntioquia.get(i).informalityPercentage();
        }
        
        average = accumulated/MunicipalitiesAntioquia.size();
        System.out.println();
        System.out.println("""
                           Using a sample of municipalities in the south of Antioquia 
                           with respect to the percentage of informal employment, 
                           a Point Estimator of the average is obtained, from which we can infer 
                           that the approximate average of the percentage of informal employment 
                           in the southern area of Antioquia is: """+average*100+"%");
        
        Double variance = 0d;
        for (int i=0; i<MunicipalitiesAntioquia.size(); i++)
        {
            variance += (MunicipalitiesAntioquia.get(i).informalityPercentage() - average)*(MunicipalitiesAntioquia.get(i).informalityPercentage() - average);
        }
        variance = variance/(MunicipalitiesAntioquia.size()-1);
        
        System.out.println();
        System.out.println("""
                           Calculating the standard deviation 
                           to observe the dispersion of the percentages of informal employment 
                           with respect to the average gives us a value of: """+Math.sqrt(variance)+""" 
                           . This means that the percentages of informal employment fluctuate on average: """+Math.sqrt(variance));
        
        Integer cumulativePopulation = 0, cumulativeEmpleoyed = 0;
        for (int i=0; i<MunicipalitiesAntioquia.size(); i++) 
        {
            cumulativeEmpleoyed += MunicipalitiesAntioquia.get(i).employedPeople();
            cumulativePopulation += MunicipalitiesAntioquia.get(i).population();
        }
        System.out.println();
        Integer informalEmployment = (int) (cumulativeEmpleoyed*average);
        
        System.out.println("The total number of people in the sample of the municipalities that we take as examples is "+cumulativePopulation+
                           ", And of these, the average number of people with informal employment is "+informalEmployment+
                           ", out of "+cumulativeEmpleoyed+" people who work in the sample area.");
        
        
        for (int i=0; i<MunicipalitiesAntioquia.size(); i++) //Sorting the list in ascending order taking into account the percentage of informality
        {
            Municipality p = MunicipalitiesAntioquia.get(i);
            Integer j = i - 1;
            
            while(j >= 0 && p.informalityPercentage() < MunicipalitiesAntioquia.get(j).informalityPercentage())
            {
                MunicipalitiesAntioquia.set(j+1, MunicipalitiesAntioquia.get(j));
                j = j - 1;
            }
            MunicipalitiesAntioquia.set(j+1, p);
        }
        
        Integer median = (int)(MunicipalitiesAntioquia.size()/2);
        Double medianInformalEmployment ;
        if(MunicipalitiesAntioquia.size()%2 == 0)
        {
            medianInformalEmployment = (MunicipalitiesAntioquia.get(median+1).informalityPercentage() + MunicipalitiesAntioquia.get(median).informalityPercentage()) / 2;
        }
        else
        {
            medianInformalEmployment = MunicipalitiesAntioquia.get(median+1).informalityPercentage();
        }
        System.out.println();
        System.out.println("The median percentage of informality in the sample area of Antioquia is "+medianInformalEmployment+"%");
        
        StringBuilder aboveTheMedian = new StringBuilder();
        StringBuilder belowMedian = new StringBuilder();
        for (int i=0; i<MunicipalitiesAntioquia.size(); i++)
        {
            if(MunicipalitiesAntioquia.get(i).informalityPercentage()>=medianInformalEmployment)
            {
                aboveTheMedian.append(MunicipalitiesAntioquia.get(i).name()).append(", ");
            }
            else
            {
                belowMedian.append(MunicipalitiesAntioquia.get(i).name()).append(", ");
            }
        }
        System.out.println();
        System.out.println("Municipalities with the highest informality rate: "+aboveTheMedian+ " They affect economic growth in Antioquia to a greater extent");
        System.out.println();
        System.out.println("Municipalities with the lowest informality rate: "+belowMedian);
        System.out.println();
        
        System.out.println("Municipalities with greater informality ");
        System.out.println(MunicipalitiesAntioquia.get(MunicipalitiesAntioquia.size()-1).name()+", "+MunicipalitiesAntioquia.get(MunicipalitiesAntioquia.size()-1).informalityPercentage()+"%");
        System.out.println(MunicipalitiesAntioquia.get(MunicipalitiesAntioquia.size()-2).name()+", "+MunicipalitiesAntioquia.get(MunicipalitiesAntioquia.size()-2).informalityPercentage()+"%");
        System.out.println(MunicipalitiesAntioquia.get(MunicipalitiesAntioquia.size()-3).name()+", "+MunicipalitiesAntioquia.get(MunicipalitiesAntioquia.size()-3).informalityPercentage()+"%");
        System.out.println(MunicipalitiesAntioquia.get(MunicipalitiesAntioquia.size()-4).name()+", "+MunicipalitiesAntioquia.get(MunicipalitiesAntioquia.size()-4).informalityPercentage()+"%");
        System.out.println();
        System.out.println("Municipality with the least informality: "+ MunicipalitiesAntioquia.get(0).name()+", "+MunicipalitiesAntioquia.get(0).informalityPercentage()+"%");
        System.out.println(); 
        System.out.println("""
                           Municipalities with a higher rate of informality can  
                           evaluate models of municipalities with less informality to apply possible 
                           successful business models in other regions or the world to generate 
                           quality jobs and economic growth in the region.""");    
        
    }

}
