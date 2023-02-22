FROM rappdw/docker-java-python
RUN pip install texttest
WORKDIR /GildedRose-Refactoring-Kata
CMD ["/bin/bash", "-c", "texttest -b ci -name `date`;texttest -b ci -coll"]
