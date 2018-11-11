# WISearch
Software that uses machine learning to predict the most effective measures to increase woman representation in STEM
## Inspiration
In the entire world, women are underrepresented in STEM fields.
Despite several efforts to revert this scenario, the gender difference in STEM fields has remained the same for decades. This fact points to the inefficiency of previous efforts in addressing this issue
The phenomenon of gender inequality in STEM seems not to relate exclusively on gender inequality. Finland, for example, excels in gender equality, but has one of the highest gender gaps in STEM college degrees.
In this context, the question I want to address is: what can we do to EFFECTIVELY decrease the gender gap in STEM?

## What it does
The final project is a simulator that predicts the percentage of Women among graduates in STEM fields based on data on educational policies, economy and politics. 
Users must answer several questions, such as "what is the employment rate?" or "what is the difference in mathematics tests between boys and girls?" and, at the end, the software will return the expected percentage difference of Woman among STEM graduates. 
The user can also chose a country among a library of 65 countries and, instead of inserting values for the questions, use the values correspondent to the situation of the chosen country in as many questions as the user likes. 
If a country is chosen, the software will also return the increase/decrease of representation of woman in STEM in the user's hypothetical situation compared to the current situation of the country.
In this context, the software could allow governments to test and introduce truly effective measures to decrease the gender gap in STEM fields. Similarly, companies and individuals would also be able to better develop solutions to increase the representation of woman in STEM fields.
Lastly, the results could allow us to create new methods of education specially designed to decrease gender inequality in diverse fields.

## How I built it
I used a machine learning algorithm to predict the percentage difference of Women among graduates in STEM fields based on:
 - Gender difference in science literacy (Programme for International Student Assessment)
 - Gender difference in intra-individual (relative) science literacy scores (Programme for International Student Assessment)
 - Gender difference in Self efficacy (Programme for International Student Assessment)
 - Gender difference in broad interest in science (Programme for International Student Assessment)
 - Gender difference in enjoyment of science (Programme for International Student Assessment)
 - Gender difference in mathematics scores (Programme for International Student Assessment)
 - Gender difference in intra-individual mathematics scores (Programme for International Student Assessment)
 - Gender difference in reading scores (Programme for International Student Assessment)
 - Gender difference in intra-individual reading scores (Programme for International Student Assessment)
 - Global gender gap index (World Economic Forum)
 - Unemployment rate (trading economics)
 - Fertility rate (the world bank)
 - Per capita GPA (CIA)
 - Government investment in education (United Nations)
 - Gini index of inequality (CIA)
 - Number of universities divided by the population (Consejo Superior de Investigaciones Científicas (CSIC) and United Nations)
 - Number of universities among the top 5,000 universities in the world divided by the population (Consejo 
 - Superior de Investigaciones Científicas (CSIC) and United Nations)

## Challenges I ran into
- Leaning machine learning on my own in order to make the project possible
- Adjusting the variables so they are in the same order of magnitude to improve the algorithm 
- Choosing a good alpha parameter for my cost function to decrease properly
- Finding good reliable data for all the countries I wanted to search
- Time management 

## Accomplishments that I'm proud of
 - Being able to learn and implement machine learning
 - Being able to decrease my cost function at each iteration (what shows the machine learning code is working)
 - Getting results that make sense according to the research projects on gender inequality on STEM I studied
 - Making something that can have a huge social impact
 - Finishing the project on time
 - Connecting with brilliant girls during the hackathon

## What I learned
The indices of woman representation in STEM has, in fact, a negative correlation with gender inequality (obtained through the global gender gap index). This result supports the results found in the paper "The Gender-Equality Paradox in Science,Technology, Engineering, and Mathematics Education" (Stoet et. all, 2018), which shows that the countries with the largest gender equality have the largest gender gaps in STEM in secondary and tertiary education. That shows that the causes of the problem may be much more complicated than what we've though and can give an insight into the reason why, despite all efforts towards decreasing gender inequality in STEM, the problem persists. 
A positive correlation was found between woman representation in STEM and indices such as intra-individual reading scores and intra-individual math scores, factors that are usually ignored in solutions trying to minimize gender inequality in STEM.

## What's next for WISearch
 - Introduce more variables (specially related to education)
 - Introduce more countries
 - Build a better user interface and a graphic representation of the result
 - Make the software available for companies and governments to use
