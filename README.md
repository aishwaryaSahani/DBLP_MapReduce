##  Distributed Computations using Apache Hadoop

---
#### Overview
 The objective of the homework is to create a distributed map-reduce program for parallel processing of the publically available [DBLP](https://dblp.uni-trier.de/xml/) dataset to fetch various statistics & insights about the dataset. The dataset contains entries for various publications at many different venues. The hurdles of the homework were to handle and parse the large XML parse(~2.8 GB) and breaking down the problems into smaller tasks which can be executed parallelly using Map-Reduce in [Apache Hadoop](https://hadoop.apache.org/docs/current/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html). This tasks were later executed on [Amazon EMR](https://aws.amazon.com/emr/?whats-new-cards.sort-by=item.additionalFields.postDateTime&whats-new-cards.sort-order=desc) which acts as a elastic and low cost solution to quickly execute the program.

#### Instructions
##### Prerequisites
1. Install [Simple Build Toolkit (SBT)](https://www.scala-sbt.org/1.x/docs/index.html)
2. Install [Hadoop](https://towardsdatascience.com/installing-hadoop-3-2-1-single-node-cluster-on-windows-10-ac258dd48aef) in your local system or use a virtual machine like [VM Workstation Pro](https://www.vmware.com/products/workstation-pro.html) and run [Horton Sandbox](https://www.cloudera.com/downloads/hortonworks-sandbox.html) which is a preconfigured Apache Hadoop installation.
3. AWS account

##### Build the jars
1. Clone the repository
2. Navigate to the project
3. Run cmd prompt in the project
4. Execute below cmd

		sbt clean compile assembly

5. Check the jar in the path `aishwarya_sahani_hw2/target/scala-2.13`

#### Execution Steps:

##### HortonWorks Execution:
1. Download and run Horton Works on your Virtual Machine.
2. Create JAR file
3. Use WinScp to copy the jar file in a path. eg /home/raj_ops
4. >ssh root@sandbox-hdp.hortonworks.com -p 2222
5. Log in as 
    >su - raj_ops
6. Place the Input file in the path eg /user/raj_ops/input
7. hadoop jar aishwarya_sahani_hw2-assembly-0.1.jar <input directory> <output directory>

##### AWS EMR Execution:
1. Create a bucket in S3.
2. Upload the dblp.xml and the jar. 
3. Create a cluster in EMR
4. Configure steps by selecting custom jar
5. Run the job
6. On completion, check the output folder
7. hadoop jar aishwarya_sahani_hw2-assembly-0.1.jar <input directory> <output directory>

##### AWS EMR Tutorial : [Youtube video tutorial](https://youtu.be/9rDD5bQn21c)

#### Output Snapshots
##### Task 1:
Top ten published authors at each venue.

		#msm,aba-sah dadzie
		#msm,milan stankovic
		#msm,matthew rowe 0001
		#msm,amparo elizabeth cano basave
		#msm,andrea varga
		#msm,giuseppe rizzo 0002
		#msm,markus strohmaier
		#msm,mena b. habib
		#msm,maurice van keulen
		#msm,katrin weller

##### Task 2:
Authors who published without interruption for N years where 10 <= N. 

        a. g. ramakrishnan,20
        a. lee swindlehurst,29
        a. martin wildberger,14
        a. nur zincir-heywood,18
        a. prasad sistla,31
        a. stephen morse,18
        aad p. a. van moorsel,15
        aamer chughtai,12
        aamer jaleel,15
        aaron d. ward,10
        aaron johnson,13
        aaron potechin,12
##### Task 3:
List of publications that contains only one author for each venue.

        #microposts,studying the role of elites in u.s. political twitter debates.
        35 years of fuzzy set theory,on lattice-based fuzzy rough sets.
        35 years of fuzzy set theory,on the intuitionistic fuzzy implications and negations. part 1.
        3d integration for noc-based soc architectures,testing 3d stacked ics containing through-silicon vias.
        3d integration for noc-based soc architectures,three-dimensional integration of integrated circuits - an introduction.
        3dv,four- and seven-point relative camera pose from oriented features.
        3dv,line association and vanishing point estimation with binary quadratic programming.
##### Task 4:
List of publications for each venue that contain the highest number of authors for each of these venues. 

        #microposts,a reverse approach to named entity extraction and linking in microposts.
        35 years of fuzzy set theory,fuzzy techniques in image processing at ghent university: summary of a 12-year journey.
        3d integration for noc-based soc architectures,influence of stacked 3d memory/cache architectures on gpus.
        3dv,depthsynth: real-time realistic synthetic data generation from cad models for 2.5d recognition.
        6g summit,benchmarking q-learning methods for intelligent network orchestration in the edge.
        @icpr,understanding human activities through 3d sensors - second international workshop, uha3ds 2016, held in conjunction with the 23rd international conference on pattern recognition, icpr 2016, cancun, mexico, december 4, 2016, revised selected papers
        a blossoming development of splines,a blossoming development of splines
##### Task 5:
List of top 100 authors in the descending order who publish with most co-authors 

        zhao lu,100
        özgür ulusoy,99
        zhehui wang,98
        yuan tang,97
        zhijian wu,96
        zhiming li,95
        zonghua gu,94
        zhiwei ye,93
        zhenyu jiang,92
        ziwei liu 0002,91
##### Task 6:
List of 100 authors who publish without any co-authors.

        Top ten published authors at each venue. 
        peter g. neumann,240
        vladimir zwass,187
        vinton g. cerf,179
        abdul-majid wazwaz,176
        edmond bianco,169
        a. martin wildberger,150
        manfred broy,145
        friedrich l. bauer,141

###### Note:
Configuration files were managed using TypeSafe Configs and loggers are managed using SLFL4J. Test cases are managed using Junit.










